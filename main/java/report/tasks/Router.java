/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.repo.reporter.fdep.FDEPOpenAccountApplyMsgBody;
import com.citics.repo.reporter.sjs.SJSMsg;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * @author luoguoliang
 *  
 */
public class Router implements Runnable {
    private final BlockingQueue inQueue;
    private final Map<String, BlockingQueue> outQueueMap;
    
    public Router(BlockingQueue inQueue){
        this.inQueue = inQueue;
        outQueueMap = new HashMap<String, BlockingQueue>();
    }
    
    public void addRoutePath(String objectCanonicalName,BlockingQueue outQueue){
        outQueueMap.put(objectCanonicalName, outQueue);
    }
    
    @Override
    public void run() {
        Object object;
        try {
            object = inQueue.take();
            System.out.println("路由:消息类型:"+object.getClass().getCanonicalName());
            BlockingQueue outQueue = findMatchedQueue(object);
            if (outQueue!=null) {
                outQueue.put(object);
            }else {
                System.out.println("不支持的消息类型:"+object.getClass().getCanonicalName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    private BlockingQueue findMatchedQueue(Object object) {
        BlockingQueue outQueue = matchDirectCanonicalName(object);
        if (outQueue==null) {
            return matchInterfaces(object);
        }else{
            return outQueue;
        }
    }

    private BlockingQueue matchDirectCanonicalName(Object object) {
        return outQueueMap.get(object.getClass().getCanonicalName());
    }
    private BlockingQueue matchInterfaces(Object object){
        BlockingQueue outQueue = null;
        Class[] interfaces = object.getClass().getInterfaces();
        for (Class c : interfaces) {
            outQueue = outQueueMap.get(c.getCanonicalName());
            if (outQueue!=null) {
                return outQueue;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        Router router = new Router(null);
        printName(router);
        FDEPOpenAccountApplyMsgBody openMsgBody = new FDEPOpenAccountApplyMsgBody();
        printName(openMsgBody);
        SJSMsg sjsMsg = new SJSMsg("");
        printName(sjsMsg);
        
    }
    private static void printName(Object object) {
        System.out.println(object.getClass().getCanonicalName());
        System.out.println(object.getClass().getName());
        System.out.println(object.getClass().getSimpleName());
        Class[] interafces = object.getClass().getInterfaces();
        for (Class class1 : interafces) {
            System.out.println(class1.getCanonicalName());
        }
    }
}
