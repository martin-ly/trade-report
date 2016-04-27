/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import com.citics.cfop.test.CfopUnitTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author luoguoliang
 *
 */

public class FDEPReporterTest extends CfopUnitTestBase {
    @Autowired
    private FDEPReporter reporter;
    
    @Test
    public void reportTofdep() throws InterruptedException{
        reporter.start();
        Thread.sleep(10000);
    }
    
}
