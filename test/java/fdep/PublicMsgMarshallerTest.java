/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import static org.junit.Assert.assertEquals;

import com.citics.cfop.test.CfopUnitTestBase;
import com.citics.repo.reporter.SystemTime;
import com.citics.repo.reporter.TimeSource;
import com.citics.repo.reporter.fdep.FDEPBizSvcEnum;
import com.citics.repo.reporter.fdep.FDEPMsgHeader;
import com.citics.repo.reporter.fdep.MsgMarshaller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;

import java.io.IOException;
import java.util.Date;

/**
 * @author luoguoliang 公共测试
 *
 */
public class PublicMsgMarshallerTest extends CfopUnitTestBase {
    @Autowired
    protected MsgMarshaller msgMarshaller;
    protected final String xmlHead = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    
    @Test
    public void fdepMsgHeaderToXmlString() throws XmlMappingException, IOException{
        FDEPMsgHeader msgHeader = constructMsgHeader(FDEPBizSvcEnum.APP_PURCHASE.getBizCode());
        String expectedHeadMsg = xmlHead + constructMsgHeadText(msgHeader);
        String actualHeadMsg = msgMarshaller.objToXmlString(msgHeader);
        
        assertEquals(expectedHeadMsg, actualHeadMsg);
    }
    
    @Test
    public void xmlTofdepMsgHeader() throws XmlMappingException, IOException{
        FDEPMsgHeader expectedMsgHeader = constructMsgHeader(FDEPBizSvcEnum.APP_PURCHASE.getBizCode());
        String headString = constructMsgHeadText(expectedMsgHeader);
        Object actualMsgHeader = msgMarshaller.xmlStringToObj(headString);
        
        assertEquals(expectedMsgHeader, actualMsgHeader);
    }
    
    protected FDEPMsgHeader constructMsgHeader(String bizSvc) {
        FDEPMsgHeader msgHeader = new FDEPMsgHeader();
        msgHeader.setCharSet("UTF-8");
        msgHeader.setFrom("000100");
        msgHeader.setTo("SDC");
        msgHeader.setBusinessMessageIdentifier("0000000002");
        msgHeader.setMessageDefinitionIdentifier("V1.0");
        msgHeader.setBusinessService(bizSvc);
        msgHeader.setCopyDuplicate("COPY");
        msgHeader.setPossibleDuplicate("T");
        return msgHeader;
    }
    private long fakeTime() {
        final long time = SystemTime.asMillis();
        SystemTime.setTimeSource(new TimeSource(){
            @Override
            public long millis() {
                return time;
            }
        });
        return time;
    }
    protected String constructMsgHeadText(FDEPMsgHeader msgHeadObj) {
        final long time = fakeTime();
        String msgHead = "<AppHdr>"
                + "<CharSet>"+msgHeadObj.getCharSet()+"</CharSet>"
                + "<Fr>"+msgHeadObj.getFrom()+"</Fr>"
                + "<To>"+msgHeadObj.getTo()+"</To>"
                + "<BizMsgIdr>"+msgHeadObj.getBusinessMessageIdentifier()+"</BizMsgIdr>"
                + "<MsgDefIdr>"+msgHeadObj.getMessageDefinitionIdentifier()+"</MsgDefIdr>"
                + "<BizSvc>"+msgHeadObj.getBusinessService()+"</BizSvc>"
                + "<CreDt>"+FDEPMsgHeader.dataFormat.format(new Date(time))+"</CreDt>"
                + "<CpyDplct>"+msgHeadObj.getCopyDuplicate()+"</CpyDplct>"
                + "<PssblDplct>"+msgHeadObj.getPossibleDuplicate()+"</PssblDplct>"
                + "</AppHdr>";
        return msgHead;
    }
}
