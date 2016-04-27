/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.sjs;

import com.citics.cfop.dao.T60OrdrExgMapper;
import com.citics.cfop.pub.Dict.COL;
import com.citics.repo.reporter.FDEPReportService;
import com.citics.repo.reporter.MsgHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author luoguoliang
 *
 */
@Configurable
public class SjszhwtConfirmMsgHandler implements MsgHandler {
    
    @Autowired 
    private transient T60OrdrExgMapper t60OrdrExgMapper;
    @Autowired
    private transient FDEPReportService fdepReportService;

    @Override
    public void execute(byte[] msgData) {
        try {
            SJSMsg msg = SJSMsg.build(DBFTypeEnum.SJSZHWT.name(), new String(msgData,"GBK"));
            String wtclbz = msg.get("WTCLBZ");//委托处理标志
//          System.out.println("综合委托确认处理");
            HashMap m = new HashMap();
            m.put(COL.ENTRT_NO, msg.get("WTHTXH"));
//            List<T60OrdrExg> orders = t60OrdrExgMapper.selectSelective(m);       
//            T60OrdrExg order = orders.get(0);
//            t60OrdrExgMapper.updateStatusByPrimaryKey(order.getORDR_SEQ_NO(), Dict.ORDR_ST_CODE.SUBMITED);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
