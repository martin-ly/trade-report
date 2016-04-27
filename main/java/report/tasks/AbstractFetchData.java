/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;

/**
 * @author luoguoliang
 *
 */
abstract public class AbstractFetchData<E> implements FetchData {
    @Resource(name="msgBodyQueue")
    private BlockingQueue msgBodyQueue;
    
    @Override
    public void run() {
        List<E> list = getAndUpdateData();
        for (E object : list) {
            try {
                System.out.println("FetchDate:"+object.toString());
                msgBodyQueue.put(convert(object));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    abstract protected List<E> getAndUpdateData();
    
    abstract protected Object convert(E object);
}
