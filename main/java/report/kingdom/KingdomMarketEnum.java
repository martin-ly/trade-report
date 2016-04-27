/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

/**
 * @author luoguoliang
 *
 */
public enum KingdomMarketEnum {
    SZJS('0'), //深交所
    SHJS('1'), //上交所
    SZT('4');  //深证通
    
    private char value;
    private KingdomMarketEnum(char value){
        this.value = value;
    }
    
    public char getValue(){
        return this.value;
    }
}
