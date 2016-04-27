/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.cfop.dao.T60OrdrSpMapper;
import com.citics.cfop.model.T60OrdrSp;
import com.citics.repo.reporter.fdep.FDEPAppPurchaseMsgBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author luoguoliang
 *
 */
@Component(value="FetchApplyPurchaseData")
public class FetchApplyPurchaseData extends AbstractFetchData {
    @Autowired
    private T60OrdrSpMapper t60OrdrSpMapper;

    protected List getAndUpdateData(){
        List list = t60OrdrSpMapper.selectByOrderStatusForUpdate(null, null, null);
        //更新业务数据为待打包
        return list;
    }
    
    protected Object convert(Object object){
        T60OrdrSp t60OrdrSp = (T60OrdrSp) object;
        FDEPAppPurchaseMsgBody msgBody = new FDEPAppPurchaseMsgBody();
        msgBody.setAppSheetSerialNo(String.valueOf(t60OrdrSp.getORDR_SEQ_NO()));
        //...
        return msgBody;
    }

}
