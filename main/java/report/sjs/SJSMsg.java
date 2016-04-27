/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.sjs;

import com.citics.repo.reporter.KeyValueMsg;
import com.citics.repo.reporter.kingdom.KingdomMarketEnum;
import com.citics.repo.reporter.kingdom.KingdomMsgFormatEnum;
import com.citics.repo.reporter.kingdom.KingdomSpec;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author luoguoliang
 *
 */
public class SJSMsg extends KeyValueMsg implements KingdomSpec {
//    public final static char DBF_SEPARATOR = 1;
    private String dbfType;
    
    public SJSMsg(String dbfType) {
        this.dbfType = dbfType;
    }
    public SJSMsg(String dbfType,char separator) {
        super(separator);
        this.dbfType = dbfType;
    }
    
    public static SJSMsg build(String dbfType, String msg) {
        SJSMsg sjsMsg = new SJSMsg(dbfType);
        sjsMsg.dataContainer.putAll(innerParse(msg,DEFAULT_SEPARATOR));
        return sjsMsg;
    }
    
    public String getDBFType() {
        return dbfType;
    }

    public byte[] encode() {
        try {
            return marsha().getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getMsgType() {
        return 1;
    }

    @Override
    public int getMsgFormat() {
        return KingdomMsgFormatEnum.KV.getFormatValue();
    }

    @Override
    public char getMarket() {
        return KingdomMarketEnum.SZJS.getValue();
    }

    @Override
    public char getInterface1() {
        if (dbfType.equals(DBFTypeEnum.SJSWT.name()) || dbfType.equals(DBFTypeEnum.SJSHB.name())) {
            return '1';
        } else if (dbfType.equals(DBFTypeEnum.SJSZHWT) || dbfType.equals(DBFTypeEnum.SJSZHHB)) {
            return '2';
        }
        return '0';
    }

}
