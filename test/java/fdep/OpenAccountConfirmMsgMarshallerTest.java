/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import static org.junit.Assert.assertEquals;

import com.citics.repo.reporter.fdep.FDEPBizSvcEnum;
import com.citics.repo.reporter.fdep.FDEPMsg;
import com.citics.repo.reporter.fdep.FDEPMsgHeader;
import com.citics.repo.reporter.fdep.FDEPOpenAccountConfirmMsgBody;

import org.junit.Test;
import org.springframework.oxm.XmlMappingException;

import java.io.IOException;

/**
 * @author luoguoliang
 *
 */
public class OpenAccountConfirmMsgMarshallerTest extends PublicMsgMarshallerTest {

    @Test
    public void xmlTofdepOpenAccountConfirmMsgBody() throws XmlMappingException, IOException {
        FDEPOpenAccountConfirmMsgBody expectedMsgBody = constructOpenAccountConfirmMsgBody();
        String bodyString = constructOpenAccountConfirmMsgBodyText(expectedMsgBody);
        Object actualMsgBody = msgMarshaller.xmlStringToObj(bodyString);

        assertEquals(expectedMsgBody, actualMsgBody);
    }

    @Test
    public void xmlTofdepWholeMsg() throws XmlMappingException, IOException {
        FDEPMsg expectedMsg = new FDEPMsg();
        FDEPMsgHeader expectedMsgHeader =
                constructMsgHeader(FDEPBizSvcEnum.OPEN_ACCOUNT_CONFIRM.getBizCode());
        expectedMsg.setMsgHeader(expectedMsgHeader);
        String headString = constructMsgHeadText(expectedMsgHeader);

        FDEPOpenAccountConfirmMsgBody expectedMsgBody = constructOpenAccountConfirmMsgBody();
        expectedMsg.addMsgBody(expectedMsgBody);
        String bodyString = constructOpenAccountConfirmMsgBodyText(expectedMsgBody);

        String msgString =
                xmlHead + "<Msg>" + headString + "<Document>" + bodyString + "</Document></Msg>";

        Object actualMsg = msgMarshaller.xmlStringToObj(msgString);

        assertEquals(expectedMsg, actualMsg);
    }

    private FDEPOpenAccountConfirmMsgBody constructOpenAccountConfirmMsgBody() {
        FDEPOpenAccountConfirmMsgBody openAccountConfirmMsgBody =
                new FDEPOpenAccountConfirmMsgBody();
        openAccountConfirmMsgBody.setIntermediariesIdentification("001001");
        openAccountConfirmMsgBody.setRelatedReference("0010");
        openAccountConfirmMsgBody.setOrderReference("E100");
        return openAccountConfirmMsgBody;
    }

    private String constructOpenAccountConfirmMsgBodyText(FDEPOpenAccountConfirmMsgBody msgBody) {
        String nsXsiTypeStr = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"java:com.citics.repo.reporter.FDEPOpenAccountConfirmMsgBody\"";
        String expected ="<Data"+" "+nsXsiTypeStr+">" + 
                    "<IntrmiesId>" + msgBody.getIntermediariesIdentification() + "</IntrmiesId>" + 
                    "<RltdRef>" + msgBody.getRelatedReference() + "</RltdRef>" + 
                    "<OrdrRef>" + msgBody.getOrderReference() + "</OrdrRef>" + "</Data>";;

        return expected.toString();
    }

}
