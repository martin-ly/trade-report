/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import com.citics.cfop.test.CfopUnitTestBase;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author luoguoliang
 *
 */

public class KingdomReporterTest extends CfopUnitTestBase {
    @Autowired
    private KingdomReporter reporter;
    
    @Test
    public void reportToKingdom() throws InterruptedException{
        reporter.start();
        Thread.sleep(5000);
        reporter.shutdown();
    }
    
}
