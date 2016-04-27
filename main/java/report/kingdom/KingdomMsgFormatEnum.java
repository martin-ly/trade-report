/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

/**
 * @author luoguoliang
 *
 */
public enum KingdomMsgFormatEnum {
    ORIGINAL(5),
    KV(6);
    
    private int formatValue;
    
    private KingdomMsgFormatEnum(int value){
        this.formatValue = value;
    }
    
    public int getFormatValue(){
        return this.formatValue;
    }
}
