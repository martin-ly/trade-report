/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

/**
 * @author luoguoliang
 *
 *  FEDP系统业务类型
 */
public enum FDEPBizSvcEnum {
    OPEN_ACCOUNT_APP("001"),
    CANCEL_ACCOUNT("002"),
    UPDATE_ACCOUNT("003"),
    OPEN_ACCOUNT_CONFIRM("101"),
    CANCEL_ACCOUNT_CONFIRM("102"),
    UPDATE_ACCOUNT_CONFIRM("103"),
    APP_PURCHASE("022"), //申购
    REDEEM("024"),       //赎回
    NETWORTH("067"),     //净值
    DISCOUNT("201");     //折扣
    
    private String bizCode;
    FDEPBizSvcEnum(String bizCode){
        this.bizCode = bizCode;
    }
    
    public String getBizCode(){
        return this.bizCode;
    }
}
