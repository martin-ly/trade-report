/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;



/**
 * @author luoguoliang
 *
 */
public interface KingdomSpec {
    
    //0系统报文，1业务报文
    int getMsgType();
    //5带业务头的业务接口原始格式。6带业务头的Key/Value格式
    int getMsgFormat();
    
    char getMarket();
    char getInterface1();

}
