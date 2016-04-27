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
public class SendKingdomHeartBeatService implements Runnable {
    private Logger log = Logger.getLogger(SendKingdomHeartBeatService.class);
    private KingdomConnection conn;

    public SendKingdomHeartBeatService(KingdomConnection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
            conn.sendHeartBeatMsg();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("发送心跳报文失败", e);
        }
    }

}
