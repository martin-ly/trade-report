/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author luoguoliang
 *
 */
public class SystemTimeAbstractionTest {
    
    @After
    public void resetTimeSource(){
        SystemTime.reset();
    }
    
    @Test
    public void returnValidTimeInMilliSeconds(){
        long befor = System.currentTimeMillis();
        long clock = SystemTime.asMillis();
        long after = System.currentTimeMillis();
        assertTrue(befor<=clock && clock <=after);
    }
    
    @Test
    public void returnFakeTimeInMilliSeconds(){
        final long fakeTime = 12345678l;
        SystemTime.setTimeSource(new TimeSource(){
            public long millis(){
                return fakeTime;
            }
        });
        assertEquals(fakeTime,SystemTime.asMillis());
    }
}
