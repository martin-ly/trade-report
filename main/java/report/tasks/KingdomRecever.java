/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.KeyValueMsg;
import com.citics.repo.reporter.MsgHandler;
import com.citics.repo.reporter.MsgHandlerFactory;
import com.citics.repo.reporter.kingdom.KingdomConnection;
import com.citics.repo.reporter.kingdom.KingdomMsg;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author luoguoliang
 *
 */
public class KingdomRecever implements Runnable {
    private Logger log = Logger.getLogger(KingdomRecever.class);
    
    private KingdomConnection conn;

    public KingdomRecever(KingdomConnection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
            KingdomMsg msg = conn.readAMessage();
            
            byte msgType = msg.getSessionHeader().getMsgType();
            String sessionMsgType = String.valueOf(msgType);
            String funcType = "";
            String market = "";
            String interface1 = "";
            String busiMsgType = "";
            if(msgType == 0){//系统报文 要解析
                KeyValueMsg kvMsg = KeyValueMsg.parse(new String(msg.getOriMsgData()));
                funcType = kvMsg.get("Function").trim();
            }else if(msgType == 1){//业务报文
                market = String.valueOf(msg.getHead().getMarket());
                interface1 = String.valueOf(msg.getHead().getInterface());
                busiMsgType = String.valueOf(msg.getHead().getMsgType());
            }
            MsgHandler handler = MsgHandlerFactory.createHandler(sessionMsgType,funcType,market,interface1,busiMsgType);
            handler.execute(msg.getOriMsgData());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("金证接收服务出错:", e);
        }
    }
    
    public void clearResource(){
        try {
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
