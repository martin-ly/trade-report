/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.kingdom.KingdomMsg;
import com.citics.repo.reporter.kingdom.KingdomSpec;

import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *
 */
abstract public class AbstractKingdomMsgConverter implements Runnable {
    private final BlockingQueue inQueue;
    private final BlockingQueue outQueue;
    
    protected AbstractKingdomMsgConverter(BlockingQueue inQueue,BlockingQueue outQueue){
        this.inQueue = inQueue;
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        byte[] msgData;
        try {
            KingdomSpec msg = (KingdomSpec) inQueue.take();
            msgData = encode(msg);
            KingdomMsg kingdomMsg = KingdomMsg.build(msg, msgData);
            outQueue.put(kingdomMsg);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    abstract protected byte[] encode(Object obj) throws Exception;
    
}
