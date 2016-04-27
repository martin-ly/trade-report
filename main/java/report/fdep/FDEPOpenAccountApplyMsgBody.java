/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author luoguoliang
 *
 */
public class FDEPOpenAccountApplyMsgBody implements FDEPMsgBody {
    private String intermediariesIdentification;
    private String orderReference;
    private String transactionDate;
    private String transactionTime;
    private String investorName;
    private String certificateType;
    private String identification;
    private String mobile;
    private String accountIdentification;
    private String businessCode;
    private String nationality;
    private String clientRiskLevel;
    private String extension;
    
    
    public String getIntermediariesIdentification() {
        return intermediariesIdentification;
    }
    public void setIntermediariesIdentification(String intermediariesIdentification) {
        this.intermediariesIdentification = intermediariesIdentification;
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
    public String getInvestorName() {
        return investorName;
    }
    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }
    public String getCertificateType() {
        return certificateType;
    }
    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }
    public String getIdentification() {
        return identification;
    }
    public void setIdentification(String identification) {
        this.identification = identification;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getClientRiskLevel() {
        return clientRiskLevel;
    }
    public void setClientRiskLevel(String clientRiskLevel) {
        this.clientRiskLevel = clientRiskLevel;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    
    @Override
    public String getBizCode() {
        return FDEPBizSvcEnum.OPEN_ACCOUNT_APP.getBizCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        FDEPOpenAccountApplyMsgBody msgBody = (FDEPOpenAccountApplyMsgBody) obj;
        return this.intermediariesIdentification.equals(msgBody.intermediariesIdentification)
                    && this.orderReference.equals(msgBody.orderReference);
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
