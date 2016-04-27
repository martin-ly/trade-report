/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.tasks;

import com.citics.cfop.dao.T60OrdrSpMapper;
import com.citics.cfop.model.T60OrdrSp;
import com.citics.cfop.pub.Dict;
import com.citics.cfop.service.system.TradeCalendarService;
import com.citics.repo.reporter.sjs.DBFTypeEnum;
import com.citics.repo.reporter.sjs.SJSMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoguoliang
 * 4.1.3    深结算非交易报送DBF接口
 * 7．   申报：对应非交易报送库（FJYBS.DBF）的定义
    8．  确认：对应非交易报送库（FJYBS.DBF）的定义
 *
 */
@Component(value="FetchFjybsData")
public class FetchFjybsData extends AbstractFetchData {
    private @Autowired TradeCalendarService cal;
    @Autowired
    private T60OrdrSpMapper t60OrdrSpMapper;
    private Integer txnDt;
    
    public List getAndUpdateData(){
        /** 获取当前最近交易日作为报盘日 */
        Integer txnDt = getTxnDate();
        List<String> BSN_CGY_CODEs = new ArrayList<String>();
        BSN_CGY_CODEs.add(Dict.BSN_CGY_CODE.ZYCK);
        BSN_CGY_CODEs.add(Dict.BSN_CGY_CODE.ZYRK);
        List<T60OrdrSp> list = t60OrdrSpMapper.selectByOrderStatusForUpdate(txnDt,Dict.ORDR_ST_CODE.NOSUBMIT,BSN_CGY_CODEs);
        //更新业务数据为待打包
        return list;
    }
    
    public Integer getTxnDate() {
        return this.txnDt;
    }
    public void setTxnDate(String date){
        this.txnDt =  Integer.parseInt(date);
    }

    public Object convert(Object object){
        T60OrdrSp t60OrdrSp = (T60OrdrSp) object;
        SJSMsg sjsMsg = new SJSMsg(DBFTypeEnum.FJYBS.name());
        sjsMsg.set("BSZJJS", "239003");
        sjsMsg.set("BSYWLB", t60OrdrSp.getBSN_CGY_CODE());
        sjsMsg.set("BSWTLX", t60OrdrSp.getENTRT_TP_CODE());
        sjsMsg.set("BSWTXH", t60OrdrSp.getENTRT_NO());
        sjsMsg.set("BSZQDM", t60OrdrSp.getSCR_CODE());
        sjsMsg.set("BSGDDM", t60OrdrSp.getSCR_AC_NO());
        sjsMsg.set("BSXWDM", t60OrdrSp.getTXN_SEAT_NO());
        sjsMsg.set("BSYWSJ", t60OrdrSp.getRPRCH_STD_SCR_CODE());
        sjsMsg.set("BSGFXZ", "");
        sjsMsg.set("BSWTSL", Long.toString(Math.round(t60OrdrSp.getENTRT_QTY())));
        sjsMsg.set("BSWTZJ", "");
        sjsMsg.set("BSWTJG", "");
        sjsMsg.set("BSBZ1", "");
        sjsMsg.set("BSBZ2", "");
        return sjsMsg;
    }


}
