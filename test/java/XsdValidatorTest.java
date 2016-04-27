/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * @author luoguoliang
 *
 */
public class XsdValidatorTest {
    
    @Test
    public void validate(){
        String xmlText = "success";
        assertTrue(XsdValidator.validate(xmlText));
        xmlText = "error";
        assertFalse(XsdValidator.validate(xmlText));
    }
}
