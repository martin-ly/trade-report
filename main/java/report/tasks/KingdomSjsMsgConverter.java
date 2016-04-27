/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.sjs.SJSMsg;

import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *
 */
public class KingdomSjsMsgConverter extends AbstractKingdomMsgConverter {
    
    
    
//    @Override
//    public void run() {
//        byte[] msgData;
//        try {
//            SJSMsg sjsMsg = (SJSMsg) inQueue.take();
//            msgData = sjsMsg.encode();
//            KingdomMsg kingdomMsg = new KingdomMsg(sjsMsg, msgData);
//            outQueue.put(kingdomMsg);
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }
//    }

    public KingdomSjsMsgConverter(BlockingQueue inQueue, BlockingQueue outQueue) {
        super(inQueue, outQueue);
    }

    @Override
    public byte[] encode(Object obj) throws Exception {
        return ((SJSMsg)obj).encode();
    }

}
