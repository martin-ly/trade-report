/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.fdep.MsgMarshaller;

import org.springframework.oxm.XmlMappingException;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *
 */
public class KingdomfdepMsgConverter extends AbstractKingdomMsgConverter {
    private final MsgMarshaller msgMarshaller;
    
    public KingdomfdepMsgConverter(BlockingQueue inQueue,BlockingQueue outQueue,MsgMarshaller msgMarshaller){
        super(inQueue,outQueue);
        this.msgMarshaller = msgMarshaller;
    }

    @Override
    public byte[] encode(Object obj) throws XmlMappingException, IOException {
        return msgMarshaller.encode(obj);
    }
    
//    @Override
//    public void run() {
//        byte[] msgData;
//        try {
//            FDEPMsg fdepMsg = (FDEPMsg) inQueue.take();
//            msgData = msgMarshaller.encode(fdepMsg);
//            KingdomMsg kingdomMsg = new KingdomMsg(fdepMsg, msgData);
//            outQueue.put(kingdomMsg);
//        } catch (XmlMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }
//    }

}
