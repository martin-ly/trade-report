package com.citics.repo.reporter;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
/**
 * @author luoguoliang
 */
public class XsdValidator {
    private static Logger log = Logger.getLogger(XsdValidator.class);
    private static Validator validator;
    
    static {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaFile = new File(XsdValidator.class.getResource("xx.xsd").getFile());
        Schema schema;
        try {
            schema = schemaFactory.newSchema(schemaFile);
            validator = schema.newValidator();
        } catch (SAXException e) {
            log.error("XsdValidator初始化失败",e);
            throw new RuntimeException("XsdValidator初始化失败",e);
        }
        
    }
    
    public static boolean validate(String xmlText) {
        try {
            Source source = new StreamSource(new ByteArrayInputStream(xmlText.getBytes("UTF-8")));
            validator.validate(source);
            return true;
        } catch (Exception e) {
            log.error("xml报文xsd校验失败",e);
            return false;
        }
    }
}
