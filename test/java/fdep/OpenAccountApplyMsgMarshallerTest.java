/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import static org.junit.Assert.assertEquals;

import com.citics.repo.reporter.fdep.FDEPBizSvcEnum;
import com.citics.repo.reporter.fdep.FDEPMsg;
import com.citics.repo.reporter.fdep.FDEPMsgHeader;
import com.citics.repo.reporter.fdep.FDEPOpenAccountApplyMsgBody;

import org.junit.Test;
import org.springframework.oxm.XmlMappingException;

import java.io.IOException;

/**
 * @author luoguoliang
 *
 */
public class OpenAccountApplyMsgMarshallerTest extends PublicMsgMarshallerTest {
    
    @Test
    public void fdepOpenAccountMsgBodyToXmlString() throws XmlMappingException, IOException{
        FDEPOpenAccountApplyMsgBody openAccountApplyMsgBody = constructOpenAccountApplyMsgBody();
        String expectedBodyMsg = xmlHead + constructOpenAccountApplyMsgBodyText(openAccountApplyMsgBody);
        String actualBodyMsg = msgMarshaller.objToXmlString(openAccountApplyMsgBody);
        
        assertEquals(expectedBodyMsg, actualBodyMsg);
    }
   
    @Test
    public void oneWholeFDEPOpenAccountMsgToXmlString() throws XmlMappingException, IOException{
        
        FDEPMsg  msg = new FDEPMsg();
        
        FDEPMsgHeader msgHeader = constructMsgHeader(FDEPBizSvcEnum.OPEN_ACCOUNT_APP.getBizCode());
        msg.setMsgHeader(msgHeader);
        
        FDEPOpenAccountApplyMsgBody openAccountApplyMsgBody = constructOpenAccountApplyMsgBody();
        msg.addMsgBody(openAccountApplyMsgBody);
        
        String expectedMsgHead = constructMsgHeadText(msgHeader);
        String expectedMsgBody = constructOpenAccountApplyMsgBodyText(openAccountApplyMsgBody);
        
        String expectedMsgText = xmlHead + "<Msg>" + expectedMsgHead + "<Document>" + expectedMsgBody + "</Document>" + "</Msg>";
        String actualMsgText = msgMarshaller.objToXmlString(msg);
        assertEquals(expectedMsgText, actualMsgText);
    }

    private FDEPOpenAccountApplyMsgBody constructOpenAccountApplyMsgBody() {
        FDEPOpenAccountApplyMsgBody openAccountApplyMsgBody = new FDEPOpenAccountApplyMsgBody();
        openAccountApplyMsgBody.setIntermediariesIdentification("001001");
        openAccountApplyMsgBody.setOrderReference("0010");
        openAccountApplyMsgBody.setBusinessCode("001");
        return openAccountApplyMsgBody;
    }
    private String constructOpenAccountApplyMsgBodyText(FDEPOpenAccountApplyMsgBody msgBody) {
        String expected = "<Data>" 
                + "<IntrmiesId>"+msgBody.getIntermediariesIdentification()+"</IntrmiesId>"
                + "<OrdrRef>"+msgBody.getOrderReference()+"</OrdrRef>"
                + "<BizCd>"+msgBody.getBusinessCode()+"</BizCd>"
                + "</Data>";
        ;
        return expected.toString();
    }
    
}
