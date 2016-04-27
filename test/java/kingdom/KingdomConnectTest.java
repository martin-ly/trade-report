/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author luoguoliang
 *
 */
public class KingdomConnectTest {
    private KingdomConnection conn;
    
    @Before
    public void connect() throws IOException{
        conn = KingdomConnection.connect("localhost",8011);
    }
    
    @Test
    public void send() throws IOException{
        byte[] msgBytes = new byte[10];
        conn.send(msgBytes);
    }
    
    @Test
    public void read() throws IOException{
        conn.readAMessage();
    }
    
    @After
    public void close() throws IOException{
        conn.close();
    }
}
