/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import com.citics.repo.reporter.kingdom.KingdomMarketEnum;
import com.citics.repo.reporter.kingdom.KingdomMsgFormatEnum;
import com.citics.repo.reporter.kingdom.KingdomSpec;
import com.citics.repo.reporter.kingdom.KingdomfdepInterfaceEnum;

import java.util.List;

/**
 * @author luoguoliang
 *
 */
public class FDEPMsg implements KingdomSpec {
    
    private FDEPMsgHeader msgHeader;
    private Document document;
//    private List msgBodyList;
    
    public FDEPMsg(){
        this.msgHeader = new FDEPMsgHeader();
        this.document = new Document();
    }

    public FDEPMsgHeader getMsgHeader() {
        return this.msgHeader;
    }

    public void setMsgHeader(FDEPMsgHeader msgHeader) {
        this.msgHeader = msgHeader;
    }

    public void addMsgBody(FDEPMsgBody msgBody) {
        document.add(msgBody);
    }
    
    public List getMsgBodyList(){
        return this.document.getMsgBodyList();
    }

    public Document getDocument() {
        return document;
    }
    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public boolean equals(Object obj) {
        FDEPMsg fdepMsg = (FDEPMsg) obj;
        
        return this.msgHeader.equals(fdepMsg.msgHeader)
                    && this.document.equals(fdepMsg.document);
    }
    
    @Override
    public String toString() {
        return "msgHead:{" + msgHeader.toString() + "}" + "bodyList:" + document.toString();
    }

    
    @Override
    public int getMsgType() {
        return 1;
    }

    //5带业务头的业务接口原始格式。6带业务头的Key/Value格式
    @Override
    public int getMsgFormat() {
        return KingdomMsgFormatEnum.ORIGINAL.getFormatValue();
    }
    
    @Override
    public char getMarket() {
        return KingdomMarketEnum.SZT.getValue();
    }

    @Override
    public char getInterface1() {
        if(FDEPBizSvcEnum.OPEN_ACCOUNT_APP.getBizCode().equals(msgHeader.getBusinessService())){
           return KingdomfdepInterfaceEnum.KH.getValue(); 
        }else if(FDEPBizSvcEnum.APP_PURCHASE.getBizCode().equals(msgHeader.getBusinessService())){
            return KingdomfdepInterfaceEnum.SS.getValue(); 
        }
        return '0';
    }
    
}
