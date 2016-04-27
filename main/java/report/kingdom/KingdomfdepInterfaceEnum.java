/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

/**
 * @author luoguoliang
 *
 */
public enum KingdomfdepInterfaceEnum {
    SS('1'),//申赎
    KH('2'); //开户
    
    private char value;
    private KingdomfdepInterfaceEnum(char value){
        this.value = value;
    }
    public char getValue() {
        return value;
    }
    
    
}
