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
public class FDEPAppPurchaseMsgBody implements FDEPMsgBody {
    
    private String appSheetSerialNo;
    private BigDecimal discountRateOfCommission;
    private String depositAcct;
    private String regionCode;
    private String currencyType;
    private String downLoaddate;
    private BigDecimal charge;
    private BigDecimal agencyFee;
    private BigDecimal totalTransFee;
    private BigDecimal confirmedVol;
    private BigDecimal confirmedAmount;
    private String fundCode;
    private BigDecimal nav;
    private String transactionDate;
    private String transactionTime;
    private BigDecimal otherFee1;
    private BigDecimal otherFee2;
    private BigDecimal otherFee3;
    private String individualOrInstitution;
    private String transactionAccountID;
    private String distributorCode;
    private BigDecimal tradingPrice;
    private String businessCode;
    private String taAccountID;
    private String taSerialNO;
    private BigDecimal stampDuty;
    private Integer validPeriod;
    private BigDecimal rateFee;
    private BigDecimal managerRealRatio;
    private BigDecimal transferFee;
    private String fromTAFlag;
    private String shareClass;
    private String feeCalculator;
    private BigDecimal handleCharge;
    private String exchangeFlag;
    private String returnCode;
    
    /**
     * @return the appSheetSerialNo
     */
    public String getAppSheetSerialNo() {
        return appSheetSerialNo;
    }
    /**
     * @param appSheetSerialNo the appSheetSerialNo to set
     */
    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }
    /**
     * @return the discountRateOfCommission
     */
    public BigDecimal getDiscountRateOfCommission() {
        return discountRateOfCommission;
    }
    /**
     * @param discountRateOfCommission the discountRateOfCommission to set
     */
    public void setDiscountRateOfCommission(BigDecimal discountRateOfCommission) {
        this.discountRateOfCommission = discountRateOfCommission;
    }
    /**
     * @return the depositAcct
     */
    public String getDepositAcct() {
        return depositAcct;
    }
    /**
     * @param depositAcct the depositAcct to set
     */
    public void setDepositAcct(String depositAcct) {
        this.depositAcct = depositAcct;
    }
    /**
     * @return the regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }
    /**
     * @param regionCode the regionCode to set
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    /**
     * @return the currencyType
     */
    public String getCurrencyType() {
        return currencyType;
    }
    /**
     * @param currencyType the currencyType to set
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    /**
     * @return the downLoaddate
     */
    public String getDownLoaddate() {
        return downLoaddate;
    }
    /**
     * @param downLoaddate the downLoaddate to set
     */
    public void setDownLoaddate(String downLoaddate) {
        this.downLoaddate = downLoaddate;
    }
    /**
     * @return the charge
     */
    public BigDecimal getCharge() {
        return charge;
    }
    /**
     * @param charge the charge to set
     */
    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }
    /**
     * @return the agencyFee
     */
    public BigDecimal getAgencyFee() {
        return agencyFee;
    }
    /**
     * @param agencyFee the agencyFee to set
     */
    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }
    /**
     * @return the totalTransFee
     */
    public BigDecimal getTotalTransFee() {
        return totalTransFee;
    }
    /**
     * @param totalTransFee the totalTransFee to set
     */
    public void setTotalTransFee(BigDecimal totalTransFee) {
        this.totalTransFee = totalTransFee;
    }
    /**
     * @return the confirmedVol
     */
    public BigDecimal getConfirmedVol() {
        return confirmedVol;
    }
    /**
     * @param confirmedVol the confirmedVol to set
     */
    public void setConfirmedVol(BigDecimal confirmedVol) {
        this.confirmedVol = confirmedVol;
    }
    /**
     * @return the confirmedAmount
     */
    public BigDecimal getConfirmedAmount() {
        return confirmedAmount;
    }
    /**
     * @param confirmedAmount the confirmedAmount to set
     */
    public void setConfirmedAmount(BigDecimal confirmedAmount) {
        this.confirmedAmount = confirmedAmount;
    }
    public String getFundCode() {
        return fundCode;
    }
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
    public BigDecimal getNav() {
        return nav;
    }
    public void setNav(BigDecimal nav) {
        this.nav = nav;
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
    public BigDecimal getOtherFee1() {
        return otherFee1;
    }
    public void setOtherFee1(BigDecimal otherFee1) {
        this.otherFee1 = otherFee1;
    }
    public BigDecimal getOtherFee2() {
        return otherFee2;
    }
    public void setOtherFee2(BigDecimal otherFee2) {
        this.otherFee2 = otherFee2;
    }
    public BigDecimal getOtherFee3() {
        return otherFee3;
    }
    public void setOtherFee3(BigDecimal otherFee3) {
        this.otherFee3 = otherFee3;
    }
    public String getIndividualOrInstitution() {
        return individualOrInstitution;
    }
    public void setIndividualOrInstitution(String individualOrInstitution) {
        this.individualOrInstitution = individualOrInstitution;
    }
    public String getTransactionAccountID() {
        return transactionAccountID;
    }
    public void setTransactionAccountID(String transactionAccountID) {
        this.transactionAccountID = transactionAccountID;
    }
    public String getDistributorCode() {
        return distributorCode;
    }
    public void setDistributorCode(String distributorCode) {
        this.distributorCode = distributorCode;
    }
    public BigDecimal getTradingPrice() {
        return tradingPrice;
    }
    public void setTradingPrice(BigDecimal tradingPrice) {
        this.tradingPrice = tradingPrice;
    }
    public String getBusinessCode() {
        return businessCode;
    }
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
    public String getTaAccountID() {
        return taAccountID;
    }
    public void setTaAccountID(String taAccountID) {
        this.taAccountID = taAccountID;
    }
    public String getTaSerialNO() {
        return taSerialNO;
    }
    public void setTaSerialNO(String taSerialNO) {
        this.taSerialNO = taSerialNO;
    }
    public BigDecimal getStampDuty() {
        return stampDuty;
    }
    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }
    public Integer getValidPeriod() {
        return validPeriod;
    }
    public void setValidPeriod(Integer validPeriod) {
        this.validPeriod = validPeriod;
    }
    public BigDecimal getRateFee() {
        return rateFee;
    }
    public void setRateFee(BigDecimal rateFee) {
        this.rateFee = rateFee;
    }
    public BigDecimal getManagerRealRatio() {
        return managerRealRatio;
    }
    public void setManagerRealRatio(BigDecimal managerRealRatio) {
        this.managerRealRatio = managerRealRatio;
    }
    public BigDecimal getTransferFee() {
        return transferFee;
    }
    public void setTransferFee(BigDecimal transferFee) {
        this.transferFee = transferFee;
    }
    public String getFromTAFlag() {
        return fromTAFlag;
    }
    public void setFromTAFlag(String fromTAFlag) {
        this.fromTAFlag = fromTAFlag;
    }
    public String getShareClass() {
        return shareClass;
    }
    public void setShareClass(String shareClass) {
        this.shareClass = shareClass;
    }
    public String getFeeCalculator() {
        return feeCalculator;
    }
    public void setFeeCalculator(String feeCalculator) {
        this.feeCalculator = feeCalculator;
    }
    public BigDecimal getHandleCharge() {
        return handleCharge;
    }
    public void setHandleCharge(BigDecimal handleCharge) {
        this.handleCharge = handleCharge;
    }
    public String getExchangeFlag() {
        return exchangeFlag;
    }
    public void setExchangeFlag(String exchangeFlag) {
        this.exchangeFlag = exchangeFlag;
    }
    public String getReturnCode() {
        return returnCode;
    }
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    
    @Override
    public String getBizCode() {
        return FDEPBizSvcEnum.APP_PURCHASE.getBizCode();
    }
    @Override
    public boolean equals(Object obj) {
        FDEPAppPurchaseMsgBody msgBody = (FDEPAppPurchaseMsgBody) obj;
        return this.businessCode.equals(msgBody.businessCode)
                    && this.appSheetSerialNo.equals(msgBody.appSheetSerialNo);
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
}
