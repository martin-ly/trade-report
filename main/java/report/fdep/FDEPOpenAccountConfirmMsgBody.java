/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author luoguoliang
 *
 */
public class FDEPOpenAccountConfirmMsgBody implements FDEPMsgBody {
    private String intermediariesIdentification;
    private String relatedReference;
    private String orderReference;
    private String transactionDate;
    private String transactionTime;
    private String returnCode;
    private String returnMessage;
    private String accountIdentification;
    private String businessCode;
    
    
    public String getIntermediariesIdentification() {
        return intermediariesIdentification;
    }
    public void setIntermediariesIdentification(String intermediariesIdentification) {
        this.intermediariesIdentification = intermediariesIdentification;
    }
    public String getRelatedReference() {
        return relatedReference;
    }
    public void setRelatedReference(String relatedReference) {
        this.relatedReference = relatedReference;
    }
    public String getOrderReference() {
        return orderReference;
    }
    public void setOrderReference(String orderReference) {
        this.orderReference = orderReference;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnMessage() {
        return returnMessage;
    }
    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
    public String getAccountIdentification() {
        return accountIdentification;
    }
    public void setAccountIdentification(String accountIdentification) {
        this.accountIdentification = accountIdentification;
    }
    public String getBusinessCode() {
        return businessCode;
    }
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
    
    @Override
    public String getBizCode() {
        return FDEPBizSvcEnum.OPEN_ACCOUNT_CONFIRM.getBizCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        FDEPOpenAccountConfirmMsgBody msgBody = (FDEPOpenAccountConfirmMsgBody) obj;
        return this.intermediariesIdentification.equals(msgBody.intermediariesIdentification)
                    && this.relatedReference.equals(msgBody.relatedReference)
                    && this.orderReference.equals(msgBody.orderReference);
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
