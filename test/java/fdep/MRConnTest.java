/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author luoguoliang
 *
 */
public class MRConnTest {
    
    @Test
    public void init(){
        try {
            MRConn.init();
            fail("不应该初始化成功");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("", true);
        }
    }
    @Test
    public void send(){
        String msg = "hello";
        MRConn.init().sendMsg(msg, "destUserID", "destAppID", "corrPKGID");
    }

}
