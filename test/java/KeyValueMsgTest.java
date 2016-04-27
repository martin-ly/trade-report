/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author luoguoliang
 *
 */
public class KeyValueMsgTest {
    
    @Test
    public void test(){
        KeyValueMsg kv = new KeyValueMsg();
        kv.set("WTT","1");
        kv.set("WTY","2");
        assertEquals("WTT=1"+KeyValueMsg.DEFAULT_SEPARATOR+"WTY=2"+KeyValueMsg.DEFAULT_SEPARATOR, kv.marsha());
        
        char customSeparator = 2;
        kv = new KeyValueMsg(customSeparator);
        kv.set("WTT","1");
        kv.set("WTY","2");
        assertEquals("WTT=1"+customSeparator+"WTY=2"+customSeparator, kv.marsha());
    }
}
