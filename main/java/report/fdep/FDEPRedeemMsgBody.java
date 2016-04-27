/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 * @author luoguoliang
 *
 */
public class FDEPRedeemMsgBody implements FDEPMsgBody {
    private String appSheetSerialNo;
    private String regionCode;

    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }
    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }
    public String getRegionCode() {
        return regionCode;
    }
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    
    @Override
    public String getBizCode() {
        return FDEPBizSvcEnum.REDEEM.getBizCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        FDEPRedeemMsgBody msgBody = (FDEPRedeemMsgBody) obj;
        return this.appSheetSerialNo.equals(msgBody.appSheetSerialNo)
                    && this.regionCode.equals(msgBody.regionCode);
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
//        return "appSheetSerialNo:"+appSheetSerialNo + ",regionCode:" + regionCode;
    }
}
