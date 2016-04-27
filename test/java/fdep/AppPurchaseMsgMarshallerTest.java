/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import static org.junit.Assert.assertEquals;

import com.citics.repo.reporter.fdep.FDEPAppPurchaseMsgBody;
import com.citics.repo.reporter.fdep.FDEPBizSvcEnum;
import com.citics.repo.reporter.fdep.FDEPMsg;
import com.citics.repo.reporter.fdep.FDEPMsgHeader;

import org.junit.Test;
import org.springframework.oxm.XmlMappingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoguoliang
 *
 */
public class AppPurchaseMsgMarshallerTest extends PublicMsgMarshallerTest {
    
    @Test
    public void fdepAppPurchaseMsgBodyToXmlString() throws XmlMappingException, IOException{
        FDEPAppPurchaseMsgBody appPurchaseMsgBody = constructAppPurchaseMsgBody();
        List list = new ArrayList();
        list.add(appPurchaseMsgBody);
        String expectedBodyMsg = xmlHead + constructAppPurchaseMsgBodyText(list,false);
        String actualBodyMsg = msgMarshaller.objToXmlString(appPurchaseMsgBody);
        
        assertEquals(expectedBodyMsg, actualBodyMsg);
    }
   
    @Test
    public void oneWholeFDEPAppPurchaseMsgWithOneBodyToXmlString() throws XmlMappingException, IOException{
        
        FDEPMsg  msg = new FDEPMsg();
        
        FDEPMsgHeader msgHeader = constructMsgHeader(FDEPBizSvcEnum.APP_PURCHASE.getBizCode());
        msg.setMsgHeader(msgHeader);
        
        FDEPAppPurchaseMsgBody appPurchaseMsgBody = constructAppPurchaseMsgBody();
        msg.addMsgBody(appPurchaseMsgBody);
        
        String expectedMsgHead = constructMsgHeadText(msgHeader);
        String expectedMsgBody = constructAppPurchaseMsgBodyText(msg.getMsgBodyList(),false);
        
        String expectedMsgText = xmlHead + "<Msg>" + expectedMsgHead + "<Document>" + expectedMsgBody + "</Document>" + "</Msg>";
        String actualMsgText = msgMarshaller.objToXmlString(msg);
        assertEquals(expectedMsgText, actualMsgText);
    }
    
    @Test
    public void oneWholeFDEPAppPurchaseMsgWithMoreThanOneBodyToXmlString() throws XmlMappingException, IOException{
        
        FDEPMsg  msg = new FDEPMsg();
        
        FDEPMsgHeader msgHeader = constructMsgHeader(FDEPBizSvcEnum.APP_PURCHASE.getBizCode());
        msg.setMsgHeader(msgHeader);
        
        msg.addMsgBody(constructAppPurchaseMsgBody());
        msg.addMsgBody(constructAppPurchaseMsgBody());
        
        String expectedMsgHead = constructMsgHeadText(msgHeader);
        String expectedMsgBody = constructAppPurchaseMsgBodyText(msg.getMsgBodyList(),false);
        
        String expectedMsgText = xmlHead + "<Msg>" + expectedMsgHead + "<Document>" + expectedMsgBody + "</Document>" + "</Msg>";
        String actualMsgText = msgMarshaller.objToXmlString(msg);

        assertEquals(expectedMsgText, actualMsgText);
    }

    @Test
    public void xmlTofdepAppPurchaseMsgBody() throws XmlMappingException, IOException{
        FDEPAppPurchaseMsgBody expectedMsgBody = constructAppPurchaseMsgBody();
        List list = new ArrayList();
        list.add(expectedMsgBody);
        String bodyString = constructAppPurchaseMsgBodyText(list,true);
        Object actualMsgBody = msgMarshaller.xmlStringToObj(bodyString);
        
        assertEquals(expectedMsgBody, actualMsgBody);
    }
    
    @Test
    public void xmlTofdepWholeMsg() throws XmlMappingException, IOException{
        FDEPMsg expectedMsg = new FDEPMsg();
        FDEPMsgHeader expectedMsgHeader = constructMsgHeader(FDEPBizSvcEnum.APP_PURCHASE.getBizCode());
        expectedMsg.setMsgHeader(expectedMsgHeader);
        String headString = constructMsgHeadText(expectedMsgHeader);
        
        //构造两条body明细
        FDEPAppPurchaseMsgBody expectedMsgBody = constructAppPurchaseMsgBody();
        expectedMsg.addMsgBody(expectedMsgBody);
        expectedMsgBody = constructAppPurchaseMsgBody();
        expectedMsg.addMsgBody(expectedMsgBody);
        String bodyString = constructAppPurchaseMsgBodyText(expectedMsg.getMsgBodyList(),true);
        
        String msgString = xmlHead + "<Msg>" + headString + "<Document>" + bodyString + "</Document></Msg>";
        
        Object actualMsg = msgMarshaller.xmlStringToObj(msgString);
        
        assertEquals(expectedMsg, actualMsg);
    }
    
    private FDEPAppPurchaseMsgBody constructAppPurchaseMsgBody() {
        FDEPAppPurchaseMsgBody appPurchaseMsgBody = new FDEPAppPurchaseMsgBody();
        appPurchaseMsgBody.setAppSheetSerialNo("0010");
        appPurchaseMsgBody.setRegionCode("E100");
        appPurchaseMsgBody.setBusinessCode("022");
        return appPurchaseMsgBody;
    }
    private String constructAppPurchaseMsgBodyText(List msgBodyList,boolean withNsAndXsiType) {
        String nsXsiTypeStr = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"java:com.citics.repo.reporter.FDEPAppPurchaseMsgBody\"";
        nsXsiTypeStr = withNsAndXsiType?" "+nsXsiTypeStr:"";
        
        StringBuffer expected = new StringBuffer();
        for (int i = 0; i < msgBodyList.size(); i++) {
            FDEPAppPurchaseMsgBody appPurchaseMsgBody = (FDEPAppPurchaseMsgBody) msgBodyList.get(i);
            expected.append("<Data"+nsXsiTypeStr+">" 
                    + "<AppNo>"+appPurchaseMsgBody.getAppSheetSerialNo()+"</AppNo>"
                    + "<RegCd>"+appPurchaseMsgBody.getRegionCode()+"</RegCd>"
                    + "<BizCd>"+appPurchaseMsgBody.getBusinessCode()+"</BizCd>"
                    + "</Data>");
            ;
        }
        
        return expected.toString();
    }
    
}
