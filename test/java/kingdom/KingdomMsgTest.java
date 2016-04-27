/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import com.citics.cfop.test.CfopUnitTestBase;
import com.citics.repo.reporter.KeyValueMsg;
import com.citics.repo.reporter.fdep.FDEPBizSvcEnum;
import com.citics.repo.reporter.fdep.FDEPMsg;
import com.citics.repo.reporter.fdep.MsgMarshaller;
import com.citics.repo.reporter.sjs.DBFTypeEnum;
import com.citics.repo.reporter.sjs.SJSMsg;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;

import java.io.IOException;

import static org.junit.Assert.*;
/**
 * @author luoguoliang
 *  
 */
public class KingdomMsgTest extends CfopUnitTestBase {
    @Autowired
    private MsgMarshaller msgMarshaller;
    
    @Test
    public void marsha(){
        SJSMsg sjsMsg = new SJSMsg(DBFTypeEnum.SJSWT.name());
        sjsMsg.set("WTHTXH", "00100");
        sjsMsg.set("WTZQDM", "00101");
        String msg = sjsMsg.marsha();
        assertEquals("WTHTXH=00100"+KeyValueMsg.DEFAULT_SEPARATOR+"WTZQDM=00101"+KeyValueMsg.DEFAULT_SEPARATOR, msg);
    }
    
    @Test
    public void generatekingdomSessionHeaderAndBizHeadAccordingfdepMsg() throws XmlMappingException, IOException{
        FDEPMsg fdepMsg = new FDEPMsg();
        fdepMsg.getMsgHeader().setBusinessService(FDEPBizSvcEnum.APP_PURCHASE.getBizCode());
        
        byte[] msgData = msgMarshaller.encode(fdepMsg);
        
        KingdomMsg kingdomMsg = KingdomMsg.build(fdepMsg, msgData);
        
        assertEquals((byte)1, kingdomMsg.getSessionHeader().getMsgType());
        assertEquals((byte)5, kingdomMsg.getSessionHeader().getMsgFormat());
        assertEquals((char)'4', kingdomMsg.getHead().getMarket());//交易市场 0 深交所 1 上交所 2 北京股转 3 证金公司 4 深证通平台
        assertEquals((char)'1', kingdomMsg.getHead().getInterface());//交易接口
        
//        byte[] sessionHeader = kingdomMsg.getSessionHeader().getByteArray();
//        char[] head = kingdomMsg.getHead().getCharArray();
//        
//        byte[] expectedSessionHeader = new byte[32];
//        char[] expectedHead = new char[64];
//        
//        assertArrayEquals(expectedSessionHeader, sessionHeader);
//        assertArrayEquals(expectedHead, head);
    } 
    @Test
    public void generatekingdomSessionHeaderAndBizHeadAccordingsjsMsg() {
        SJSMsg sjsMsg = new SJSMsg(DBFTypeEnum.SJSWT.name());
        sjsMsg.set("WTHTXH", "00100");
        sjsMsg.set("WTZQDM", "00101");
        
        byte[] msgData = sjsMsg.encode();
        
        KingdomMsg kingdomMsg = KingdomMsg.build(sjsMsg,msgData);
        
        assertEquals((byte)1, kingdomMsg.getSessionHeader().getMsgType());
        assertEquals((byte)6, kingdomMsg.getSessionHeader().getMsgFormat());
        assertEquals((char)'0', kingdomMsg.getHead().getMarket());//交易市场 0 深交所 1 上交所 2 北京股转 3 证金公司 4 深证通平台
        assertEquals((char)'1', kingdomMsg.getHead().getInterface());//交易接口
    } 
//    @Test
//    public void generateKingdomMsgByteArrayAccordingsjsMsg() throws XmlMappingException, IOException{
//        SJSMsg sjsMsg = new SJSMsg(DBFTypeEnum.SJSWT.name());
//        sjsMsg.set("WTHTXH", "00100");
//        sjsMsg.set("WTZQDM", "00101");
//        
//        byte[] msgData = sjsMsg.encode();
//        
//        KingdomMsg kingdomMsg = KingdomMsg.build(sjsMsg,msgData);
//        
//        byte[] data = kingdomMsg.getEncodedData();
//        
//        System.out.println(new String(data));
//    }
    
    @Test
    public void generateKingdomHeartBeatMsg(){
        KingdomMsg kingdomMsg = KingdomMsg.buildHeartBeatMsg();
        assertEquals((byte)0, kingdomMsg.getSessionHeader().getMsgType());
        assertEquals((byte)6, kingdomMsg.getSessionHeader().getMsgFormat());
        
        byte[] msgData = kingdomMsg.getOriMsgData();
        KeyValueMsg kvMsg = KeyValueMsg.parse(new String(msgData));
        assertEquals("1", kvMsg.get("BizType"));//系统功能类别
        assertEquals("1",kvMsg.get("Function"));//BizType
    }
    
}
