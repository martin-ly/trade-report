/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import com.citics.cfop.pub.Parm;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianxiaobin
 * 
 */
public class JZSTGConnecter {
    private Logger log = Logger.getLogger(this.getClass());

    private Socket socket;
    private String addr;
    private int port;
    private transient long lastHeartTime;//对方最近心跳时间戳 毫秒
    private transient long timeOut;// 秒
    
    private transient boolean socketOk = false;
    
    private Object sendLock = new Object();
    private Object recvLock = new Object();
    
    public JZSTGConnecter(String addr, int port) throws IOException {
        this.addr = addr;
        this.port = port;
        connect();
    }
    
    private void connect() throws IOException {
        if(socket!=null){
            try{
                socket.close();
            }catch(IOException e){
            }
        }
        socket = new Socket();
        SocketAddress socketAddr = new InetSocketAddress(addr, port);
        socket.connect(socketAddr, 10000);
        
        //连上了设置时间戳为当前时间
        lastHeartTime = System.currentTimeMillis();
        socketOk = true;
        //每次连接取一次超时时间
        timeOut = Long.parseLong(Parm.getParmValue(Parm.P3001));
    }
    
    public void reconnect() {
        for(int time=0;time<3;time++){
            synchronized (sendLock) {
                synchronized (recvLock) {
                    try {
                        connect();
                        
                        socketOk = true;
                        sendLock.notifyAll();
                        recvLock.notifyAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("重连服务器'"+addr+":"+port+"'失败! 第"+time+"次",e);
                    }
                }
            }
            if(socketOk){
                return;
            }
        }
    }

    public void send(JZMsg msg) {
        synchronized (sendLock) {
            while(!socketOk) {
                try {
                    sendLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
            
            byte[] bytes=msg.encode();
            if(bytes==null) return;
            OutputStream out;
            try {
                out = socket.getOutputStream();
                out.write(bytes);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("send失败! 服务器'"+addr+":"+port+"'", e);
                
                socketOk = false;
            }
        }
        
    }
    public JZMsg receive() {
        // read并验证同步头（2字节）：逐个字节读取，直到读到同步头，否则抛弃
        // 0x5BB4 ,java 是有符号数，转换后高位为91，低位为-76 低位在前
        synchronized (recvLock) {
            while(!socketOk) {
                try {
                    recvLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            try {
                byte low = readData(1)[0];
                byte high = readData(1)[0];
                while (true) {
                    if (91 == high && -76 == low) {
                        break;
                    } else {// 继续往后读一个字节
                        high = low;
                        low = readData(1)[0];
                    }
                }
                // read 金证头
                byte[] others = readData(JZMsg.headerLength + JZMsg.headLength - 2); // 会话头+报文头 -同步头
                byte[] header = new byte[JZMsg.headerLength + JZMsg.headLength];
                header[0] = low;
                header[1] = high;
                System.arraycopy(others, 0, header, 2, others.length);
                // 从header获取报文长度,简单验证值的合法性
                int size = JZMsg.getReportSize(header);
                if (size < 0) {
                    log.error("!!!!!!Invalid report size :" + size + ",bytes will be discard:"
                            + getInts(header));
                    return null;
                }
                // read 交易所字段
                int bodySize = size - JZMsg.headerLength - JZMsg.headLength;
                byte[] body = readData(bodySize);
                byte[] report = new byte[JZMsg.headerLength + JZMsg.headLength + body.length];
                System.arraycopy(header, 0, report, 0, header.length);
                System.arraycopy(body, 0, report, JZMsg.headerLength + JZMsg.headLength, body.length);
                // 校验 报文校验和 Int8 按深交所第5版办法校验 ,范围:从报文协议开始的所有报文字符的简单校验和
                byte expected = JZMsg.GenerateCheckSum(report, 5, report.length - 5);
                byte actual = header[4];
                if (expected != actual) {// 校验失败，丢弃本报文，log error
                    log.error("!!!!!!Report checksum failed,char:" + getChars(report) + ",byte ints:"
                            + getInts(report));
                    return null;
                }
                
                JZMsg m = JZMsg.decode(report);
                return m;
            } catch (IOException e) {
                e.printStackTrace();
                log.error("send失败! 服务器'"+addr+":"+port+"'", e);
                
                socketOk = false;
            }
            
            return null;
        }
        
    }
    
    public JZMsg generateHeartMsg() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("BizType", "1");
        data.put("Function", "1");
        JZMsg m = new JZMsg(0,6,data);
        return m;
    }

    public void handleHeartMsg(JZMsg m) {
        // 刷新stg心跳时间 
        lastHeartTime = System.currentTimeMillis();
        System.out.println("******"+m.toString());
    }
    
    //检查对方心跳是否超时/socketOk是否true，必要时重新连接
    public String checkConnection(){
        long currentTime = System.currentTimeMillis();
        boolean isTimeOut = (currentTime - lastHeartTime)/1000 >= timeOut;
        if(!socketOk || isTimeOut) reconnect();
        return null;//will not put into queue 
    }

    private byte[] readData(int size) throws IOException {
        byte[] data = new byte[size];
        int len = 0;
        while (len < size) {
            int readLen = socket.getInputStream().read(data, len, size - len);
            log.debug("***[" + getInts(data) + "]");
            len += readLen;
        }
        return data;
    }

    private String getChars(byte[] report) {
        StringBuffer sb = new StringBuffer();
        for (byte b : report) {
            sb.append((char) b).append(";");
        }
        return sb.toString();
    }

    private String getInts(byte[] report) {
        StringBuffer sb = new StringBuffer();
        for (byte b : report) {
            sb.append(b).append(";");
        }
        return sb.toString();
    }
    
}
