/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import com.citics.repo.reporter.tasks.FDEPPacker;
import com.citics.repo.reporter.tasks.FDEPSender;
import com.citics.repo.reporter.tasks.FetchData;
import com.citics.repo.reporter.tasks.Router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author luoguoliang
 *
 */
@Component
public class FDEPReporter {
    @Autowired
    @Qualifier(value="FetchOpenAccountApplyData")
    private FetchData fetchOpenAccoutApplyData;
    @Autowired
    @Qualifier(value="FetchApplyPurchaseData")
    private FetchData fetchApplyPurchaseData;
    
    @Resource(name="msgBodyQueue")
    private BlockingQueue msgBodyQueue;
    
    private Router router;
    @Resource(name="fdepMsgBodyQueue")
    private BlockingQueue fdepMsgBodyQueue;
    @Resource(name="sjsMsgQueue")
    private BlockingQueue sjsMsgQueue;
    
    private FDEPPacker packer;
    @Resource(name="fdepMsgQueue")
    private BlockingQueue fdepMsgQueue;
    
    private FDEPSender fdepSender;
    @Autowired
    private MsgMarshaller msgMarshaller;
    
    private ScheduledExecutorService es;
    
    @PostConstruct
    public void init() {
        es = Executors.newScheduledThreadPool(10);
        
        router = new Router(msgBodyQueue);
        router.addRoutePath("com.citics.repo.reporter.fdep.FDEPMsgBody", fdepMsgBodyQueue);
//        router.addRoutePath("com.citics.repo.reporter.sjs.SJSMsg", sjsMsgQueue);
        packer = new FDEPPacker(fdepMsgBodyQueue, fdepMsgQueue);
        fdepSender = new FDEPSender(fdepMsgQueue, msgMarshaller);
        
//        start();
    }
    public void start(){
        es.scheduleAtFixedRate(fetchOpenAccoutApplyData, 0, 100, TimeUnit.MILLISECONDS);
//      es.scheduleAtFixedRate(fetchApplyPurchaseData, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(router, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(packer, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(fdepSender, 0, 100, TimeUnit.MILLISECONDS);
    }
    public void shutdown(){
        es.shutdown();
    }
    
}
