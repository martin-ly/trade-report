/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author luoguoliang
 *
 */
@Component
public class MsgMarshaller {
    @Autowired
    private Marshaller marshaller;
    @Autowired
    private Unmarshaller unMarshaller;
    
    
    public byte[] encode(Object obj) throws XmlMappingException, IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(bos);
        marshaller.marshal(obj, result);
        return bos.toByteArray();
    }
    
    public String objToXmlString(Object obj) throws XmlMappingException, IOException {
        return new String(encode(obj),"UTF-8");
    }

    public Object xmlStringToObj(String xmlString) throws XmlMappingException, IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        StreamSource source = new StreamSource(bis);
        return unMarshaller.unmarshal(source);
    }
    
}
