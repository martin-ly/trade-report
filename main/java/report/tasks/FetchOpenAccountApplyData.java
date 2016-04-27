/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.cfop.dao.T60TxnScrAcMapper;
import com.citics.cfop.model.T60TxnScrAc;
import com.citics.repo.reporter.fdep.FDEPBizSvcEnum;
import com.citics.repo.reporter.fdep.FDEPOpenAccountApplyMsgBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luoguoliang
 *
 */
@Component(value="FetchOpenAccountApplyData")
public class FetchOpenAccountApplyData extends AbstractFetchData {
    @Autowired
    private T60TxnScrAcMapper t60TxnScrAcMapper;
    
    public List getAndUpdateData(){
        List list = t60TxnScrAcMapper.selectUsedForReport();
        //更新业务数据为待打包
        return list;
    }
    
    public Object convert(Object object){
        T60TxnScrAc t60TxnScrAc = (T60TxnScrAc) object;
        FDEPOpenAccountApplyMsgBody msgBody = new FDEPOpenAccountApplyMsgBody();
        msgBody.setIntermediariesIdentification("CS");//证券公司编码(中信证券:CS)
        msgBody.setOrderReference(t60TxnScrAc.getORDR_SEQ_NO());
        msgBody.setTransactionDate(String.valueOf(t60TxnScrAc.getRQS_DT()));
        msgBody.setTransactionTime(String.valueOf(t60TxnScrAc.getRQS_TM()));
        msgBody.setInvestorName(t60TxnScrAc.getCUST_NM());
        msgBody.setCertificateType(t60TxnScrAc.getDOC_TP_CODE());
        msgBody.setIdentification(t60TxnScrAc.getDOC_NO());
        msgBody.setMobile(t60TxnScrAc.getMBL_NO());
        msgBody.setAccountIdentification("");//质权人编号
        msgBody.setBusinessCode(FDEPBizSvcEnum.OPEN_ACCOUNT_APP.getBizCode());
        msgBody.setNationality("");//投资者国籍
        msgBody.setClientRiskLevel("");
        msgBody.setExtension("");
        return msgBody;
    }


}
