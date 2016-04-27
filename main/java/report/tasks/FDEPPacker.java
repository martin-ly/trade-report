/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.fdep.FDEPMsg;
import com.citics.repo.reporter.fdep.FDEPMsgBody;

import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *
 */
public class FDEPPacker implements Runnable {
    private final BlockingQueue inQueue; 
    private final BlockingQueue outQueue;
    
    public FDEPPacker(BlockingQueue inQueue,BlockingQueue outQueue){
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        FDEPMsgBody msgBody;
        try {
            msgBody = (FDEPMsgBody)inQueue.take();
            System.out.println("打包:"+msgBody);
            FDEPMsg fdepMsg = new FDEPMsg();
            fdepMsg.getMsgHeader().setBusinessMessageIdentifier("00100001");//
            fdepMsg.getMsgHeader().setBusinessService(msgBody.getBizCode());
            fdepMsg.addMsgBody(msgBody);
            outQueue.put(fdepMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
