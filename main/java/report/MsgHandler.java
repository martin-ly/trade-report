/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

/**
 * @author luoguoliang
 *
 */
public interface MsgHandler {
    public void execute(byte[] msgData);
}
