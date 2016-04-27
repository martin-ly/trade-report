/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import java.util.Date;

/**
 * @author luoguoliang
 *
 */
public class SystemTime {
    
    private static TimeSource defaultTimeSource = new TimeSource(){
        @Override
        public long millis() {
            return System.currentTimeMillis();
        }
    };
    
    private static TimeSource timeSource;

    public static long asMillis() {
        return getTimeSource().millis();
    }
    
    public static Date asDate() {
        return new Date(asMillis());
    }
    
    public static void reset() {
        setTimeSource(null);
    }
    
    private static TimeSource getTimeSource(){
        return timeSource == null ? defaultTimeSource : timeSource;
    }
    
    public static void setTimeSource(TimeSource timeSource) {
        SystemTime.timeSource = timeSource;
    }

}
