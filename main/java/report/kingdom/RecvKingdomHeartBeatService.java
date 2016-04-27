/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * @author luoguoliang
 * 心跳服务
 *
 */
public class RecvKingdomHeartBeatService implements Runnable {
    private Logger log = Logger.getLogger(RecvKingdomHeartBeatService.class);
    private KingdomConnection conn;

    public RecvKingdomHeartBeatService(KingdomConnection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
            conn.readAMessage();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("接收心跳报文失败", e);
        }
    }

}
