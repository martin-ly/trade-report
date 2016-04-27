/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.fdep.FDEPMsg;
import com.citics.repo.reporter.fdep.MRConn;
import com.citics.repo.reporter.fdep.MsgMarshaller;

import org.springframework.oxm.XmlMappingException;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *
 */
public class FDEPSender implements Runnable {
    private final BlockingQueue inQueue;
    private final MsgMarshaller msgMarshaller;
    
    public FDEPSender(BlockingQueue inQueue,MsgMarshaller msgMarshaller){
        this.inQueue = inQueue;
        this.msgMarshaller = msgMarshaller;
    }

    @Override
    public void run() {
        FDEPMsg fdepMsg;
        try {
            fdepMsg = (FDEPMsg) inQueue.take();
            String msgText = msgMarshaller.objToXmlString(fdepMsg);
            System.out.println("发往FDEP:");
            System.out.println(msgText);
            MRConn.init().sendMsg(msgText, "destUserID", "destAppID", null);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
            Thread.currentThread().interrupt();
        }catch (XmlMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
