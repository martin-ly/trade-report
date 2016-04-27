/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author luoguoliang
 *
 */
public class DBFMsgTest {
    
    @Test
    public void parseDbfMsg(){
        char separator = JZMsg.separatorChar;
        String dbfMsg = "QRLSHM=1"+separator+"QRZJJS=239003"+separator+"QRYWLB=70"+separator+"QRWTLX=S"+separator+"QRWTXH=X1201728"+separator+""
                + "QRZQDM=127002"+separator+"QRGDDM=wyue2"+separator+"QRXWDM=239003"+separator+"QRYWSJ=132006"+separator+"QRGFXZ="+separator+"QRWTSL=10000"+separator+""
                + "QRWTZJ="+separator+"QRWTJG="+separator+"QRBZ1="+separator+"QRBZ2="+separator+"QRDFXW="+separator+"QRDFGD="+separator+"QRDFXZ="+separator+""
                + "QRNR1="+separator+"QRNR2="+separator+"QRRQ1="+separator+"QRRQ2="+separator+"QRWTRQ="+separator+"QRWTSJ=12121212"+separator+""
                + "QRCWDH="+separator+"QRQSBZ="+separator+"";
        Map resultMap = JZMsg.parseKeyValueContent(dbfMsg);
        assertEquals("1", resultMap.get("QRLSHM"));
        assertEquals("239003", resultMap.get("QRZJJS"));
        assertEquals("70", resultMap.get("QRYWLB"));
        assertEquals("S", resultMap.get("QRWTLX"));
        assertEquals("X1201728", resultMap.get("QRWTXH"));
        assertEquals("127002", resultMap.get("QRZQDM"));
        assertEquals("wyue2", resultMap.get("QRGDDM"));
        assertEquals("239003", resultMap.get("QRXWDM"));
        assertEquals("132006", resultMap.get("QRYWSJ"));
        assertEquals("10000", resultMap.get("QRWTSL"));
        assertEquals("12121212", resultMap.get("QRWTSJ"));
        assertEquals("", resultMap.get("QRCWDH"));
        
        assertTrue(resultMap.get("XXXX")==null);
    }
    
//    @Test
//    public void parseIrregularDbfMsg(){
//        String irregularDbfMsg = "QRLSHM"+separator+"QRZJJS=239003"+separator+""+separator+"QRYWLB==70"+separator+"";
//        try{
//            Map resultMap = ReportHelper.parseKeyValue(irregularDbfMsg);
//        }catch(Exception e){
//            
//        }
//    }

}
