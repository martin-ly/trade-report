/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author luoguoliang
 *
 */
public class KingdomConnection {
    private Socket socket;
    
    
    private KingdomConnection(String ip,int port) throws IOException{
        socket = new Socket();
        SocketAddress socketAddr = new InetSocketAddress(ip, port);
        socket.connect(socketAddr, 10000);
    }

    public static KingdomConnection connect(String ip, int port) throws IOException {
        return new KingdomConnection(ip,port);
    }

    public void send(byte[] msgBytes) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(msgBytes);
        out.flush();
    }
    
    public void sendHeartBeatMsg() throws IOException{
        //构造一个金证心跳报文
        KingdomMsg msg = KingdomMsg.buildHeartBeatMsg();
        byte[] data = msg.getEncodedData();
        //发送
        send(data);
    }

    public KingdomMsg readAMessage() throws IOException {
        InputStream in = socket.getInputStream();
        
        byte[] syncHeadData = readUtilSessionSyncHead(in);
        
        //读取会话头除同步头其他部分30个字节
        byte[] sessionRemainData = readData(in,30);
        byte[] sessionHeadData = new byte[32];
        System.arraycopy(syncHeadData, 0, sessionHeadData, 0, 2);
        System.arraycopy(sessionRemainData, 0, sessionHeadData, 2, 30);
        SessionHeader sessionHeader = SessionHeader.build(sessionHeadData);
        
        int len = 32;
        char[] busiData = null;
        if(sessionHeader.getMsgType() == 1){//业务报文还要读取业务头，系统报文跳过直接读取报文体
            //读取业务头
            byte[] busiHeadData = readData(in,64);
            busiData = new String(busiHeadData).toCharArray();
            len = 32+64;
        }
        //读取报文体
        byte[] msgBodyData = readData(in, sessionHeader.getMsgLen()-len);
        
        return KingdomMsg.build(sessionHeadData, busiData, msgBodyData);
    }
    
    //一直读到会话同步头。
    private byte[] readUtilSessionSyncHead(InputStream in) throws IOException {
        int low = readData(in,1)[0];
        int high = readData(in,1)[0];
        while(low!=-76 || high!=91){
            low = high;
            high = readData(in,1)[0];
        }
        return new byte[]{(byte)low,(byte)high};
    }

    private byte[] readData(InputStream in, int readNum) throws IOException {
        byte[] data = new byte[readNum];
        int off = 0;
        int numPerRead = 0;
        int remainNum = readNum;
        do{
            numPerRead = in.read(data, off, remainNum);
            off += numPerRead;
            remainNum = remainNum - numPerRead;
        }while(numPerRead == -1);
        
        return data;
    }

    public void close() throws IOException {
        socket.close();
    }
    
    public static void main(String[] args) {
        char market = '0';
        char interface1 = '2';
        char msgType = '3';
        System.out.println(String.valueOf(market)+interface1+msgType);
    }

}
