/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author luoguoliang
 *
 */
public class SignTest {
    
    @Test
    public void sign(){
        String plainText = "<Data><FndNm>融通通汇1号</FndNm><TtlFndVol>4164745.00</TtlFndVol><FndCd>119543</FndCd><FndSts>0</FndSts><Nav>1.2325</Nav><CcyTp>156</CcyTp><NtValDt>20140421</NtValDt><NtValTp>0</NtValTp></Data>";
//        Document doc = DocumentHelper.createDocument();
//        doc.setXMLEncoding("UTF-8");
//        Element root = doc.addElement("Msg");
//        Element Data = root.addElement("Document").addElement("Data");
//        Data.addElement("FndNm").setText("融通通汇1号");
//        Data.addElement("TtlFndVol").setText("4164745.00");
//        Data.addElement("FndCd").setText("119543");
//        Data.addElement("FndSts").setText("0");
//        Data.addElement("Nav").setText("1.2325");
//        Data.addElement("CcyTp").setText("156");
//        Data.addElement("NtValDt").setText("20140421");
//        Data.addElement("NtValTp").setText("0");
        String cipherText = "ZZ/mEUvcIFxci6QIdXGIuQ==";
//        plainText = Data.asXML();
        assertEquals(cipherText, Sign.sign(plainText));
    }
}
