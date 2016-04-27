/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import com.citics.cfop.test.CfopUnitTestBase;
import com.citics.repo.reporter.sjs.SjszhwtConfirmMsgHandler;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author luoguoliang
 *
 */
public class BlockingQueueInjectionTest extends CfopUnitTestBase {
    
    @Test
    public void injection(){
        SjszhwtConfirmMsgHandler sjsMsgHandler = new SjszhwtConfirmMsgHandler();
        Assert.assertNotNull(ReflectionTestUtils.getField(sjsMsgHandler, "sjsMsgQueue"));
    }

}
