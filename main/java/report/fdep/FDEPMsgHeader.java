/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import com.citics.repo.reporter.SystemTime;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author luoguoliang
 *
 */
public class FDEPMsgHeader {
    
    public static DateFormat dataFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    
    public FDEPMsgHeader(){
        init();
    }

    private void init() {
        charSet = "UTF-8";
        from = "000100"; 
        to = "SDC";
        creationDate = dataFormat.format(SystemTime.asDate());
        messageDefinitionIdentifier = "V1.0";
    }
    
    private String charSet;//暂指定UTF-8
    private String from;//发起报文的业务系统标识符；目前填写消息发送方的结算账户（资金席位）
    private String to;//接受报文的业务系统标识符
    private String businessMessageIdentifier;//业务报文的标识符
    private String messageDefinitionIdentifier;//报文体格式版本标识符目前固定为“V1.0”
    private String businessService;//业务类型标识符，具体参见附录
    private String creationDate;//报文的创建时间
    private String copyDuplicate;//是否报文副本或重发的报文。COPY：报文副本 DUPL：重发的报文 CODU：重发的报文副本
    private String possibleDuplicate;//报文是否可能重复。T：可能重复 F：不可能重复
    private String validateResult;//结算系统返回时用,表明是否通过校验,具体定义见附录
    private String related;//报文的参考或者引用信息，例如:反馈时存放源报文的BizMsgIdr；对于未通过校验的报文为错误代码。
    private String signature;//报文的数字签名，对报文体的MD5值进行Base64编码后获得的字符串
    
    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBusinessMessageIdentifier() {
        return businessMessageIdentifier;
    }

    public void setBusinessMessageIdentifier(String businessMessageIdentifier) {
        this.businessMessageIdentifier = businessMessageIdentifier;
    }

    public String getMessageDefinitionIdentifier() {
        return messageDefinitionIdentifier;
    }

    public void setMessageDefinitionIdentifier(String messageDefinitionIdentifier) {
        this.messageDefinitionIdentifier = messageDefinitionIdentifier;
    }

    public String getBusinessService() {
        return businessService;
    }

    public void setBusinessService(String businessService) {
        this.businessService = businessService;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCopyDuplicate() {
        return copyDuplicate;
    }

    public void setCopyDuplicate(String copyDuplicate) {
        this.copyDuplicate = copyDuplicate;
    }

    public String getPossibleDuplicate() {
        return possibleDuplicate;
    }

    public void setPossibleDuplicate(String possibleDuplicate) {
        this.possibleDuplicate = possibleDuplicate;
    }

    public String getValidateResult() {
        return validateResult;
    }

    public void setValidateResult(String validateResult) {
        this.validateResult = validateResult;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
   
    @Override
    public boolean equals(Object obj) {
        FDEPMsgHeader msgHeader = (FDEPMsgHeader) obj;
        return this.from.equals(msgHeader.from)
                        && this.businessService.equals(msgHeader.businessService)
                        && this.businessMessageIdentifier.equals(msgHeader.businessMessageIdentifier)
                        && this.creationDate.equals(msgHeader.creationDate);
    }
    
    @Override
    public String toString() {
        
        return ReflectionToStringBuilder.toString(this);
        
//        return "from:" + this.from + ",businessService:" + this.businessService + ",businessMessageIdentifier:" 
//                + this.businessMessageIdentifier + ",creationDate:"
//                + this.creationDate ;
    }

}
