/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.kingdom.KingdomConnection;
import com.citics.repo.reporter.kingdom.KingdomMsg;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *
 */
public class KingdomSender implements Runnable {
    private Logger log = Logger.getLogger(KingdomSender.class);
    private final BlockingQueue inQueue;
    
    private KingdomConnection conn;
    
    public KingdomSender(KingdomConnection conn,BlockingQueue inQueue){
        this.inQueue = inQueue;
        this.conn = conn;
    }
    
    @Override
    public void run() {
        try {
            KingdomMsg kingdomMsg = (KingdomMsg) inQueue.take();
            byte[] data = kingdomMsg.getEncodedData();
            log.info(new String(data));
            conn.send(data);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
            //异常处理
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
