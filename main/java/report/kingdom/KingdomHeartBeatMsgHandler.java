/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import com.citics.repo.reporter.MsgHandler;

/**
 * @author luoguoliang
 *
 */
public class KingdomHeartBeatMsgHandler implements MsgHandler {

    @Override
    public void execute(byte[] msgData) {
        System.out.println("更新最近心跳时间");
    }

}
