/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import com.citics.repo.reporter.fdep.MsgMarshaller;
import com.citics.repo.reporter.tasks.FDEPPacker;
import com.citics.repo.reporter.tasks.FetchData;
import com.citics.repo.reporter.tasks.FetchFjybsData;
import com.citics.repo.reporter.tasks.KingdomRecever;
import com.citics.repo.reporter.tasks.KingdomSender;
import com.citics.repo.reporter.tasks.KingdomSjsMsgConverter;
import com.citics.repo.reporter.tasks.KingdomfdepMsgConverter;
import com.citics.repo.reporter.tasks.Router;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
public class KingdomReporter {
    private Logger log = Logger.getLogger(KingdomReporter.class);
    
    @Autowired
    @Qualifier(value="FetchOpenAccountApplyData")
    private FetchData fetchOpenAccoutApplyData;
    @Autowired
    @Qualifier(value="FetchApplyPurchaseData")
    private FetchData fetchApplyPurchaseData;
    @Autowired
    @Qualifier(value="FetchFjybsData")
    private FetchData fetchFjybsData;
    
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
    
    private KingdomfdepMsgConverter fdepMsgConverter;
    @Resource(name="kingdomMsgQueue")
    private BlockingQueue kingdomMsgQueue;
    @Autowired
    private MsgMarshaller msgMarshaller;
    
    private KingdomSjsMsgConverter sjsMsgConverter;
    
    private KingdomSender kingdomSender;
    
    private KingdomRecever kingdomRecever;
    
    private ScheduledExecutorService es;
    
    @PostConstruct
    public void init(){
        //连接金证报盘服务
        KingdomConnection reportConn;
        try {
            reportConn = KingdomConnection.connect("127.0.0.1", 8012);
        } catch (IOException e) {
            log.error("连接金证报盘服务失败", e);
            return;
//            throw new RuntimeException("连接金证报盘服务失败", e);
        }
        
        //连接金证回报服务
        KingdomConnection repayConn;
        try {
            repayConn = KingdomConnection.connect("127.0.0.1", 8011);
        } catch (IOException e) {
            log.error("连接金证回报服务失败", e);
            return;
//            throw new RuntimeException("连接金证回报服务失败", e);
        }
        
        es = Executors.newScheduledThreadPool(10);
        ((FetchFjybsData) fetchFjybsData).setTxnDate("20141204");
        router = new Router(msgBodyQueue);
        router.addRoutePath("com.citics.repo.reporter.fdep.FDEPMsgBody", fdepMsgBodyQueue);
        router.addRoutePath("com.citics.repo.reporter.sjs.SJSMsg", sjsMsgQueue);
        packer = new FDEPPacker(fdepMsgBodyQueue, fdepMsgQueue);
        fdepMsgConverter = new KingdomfdepMsgConverter(fdepMsgQueue, kingdomMsgQueue, msgMarshaller);
        sjsMsgConverter = new KingdomSjsMsgConverter(sjsMsgQueue, kingdomMsgQueue);
        
        SendKingdomHeartBeatService reportSendHeartBeatService = new SendKingdomHeartBeatService(reportConn);
        KingdomRecever reportRecvHeartBeatService = new KingdomRecever(reportConn);
        kingdomSender = new KingdomSender(reportConn,kingdomMsgQueue);
        
        SendKingdomHeartBeatService repaySendHeartBeatService = new SendKingdomHeartBeatService(repayConn);
        kingdomRecever = new KingdomRecever(repayConn);
        
//        start();
    }
    
    public void start(){
        System.out.println("启动金证报盘服务...");
//        es.scheduleAtFixedRate(fetchOpenAccoutApplyData, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(fetchFjybsData, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(router, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(packer, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(fdepMsgConverter, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(sjsMsgConverter, 0, 100, TimeUnit.MILLISECONDS);
        es.scheduleAtFixedRate(kingdomSender, 0, 100, TimeUnit.MILLISECONDS);
        //报文接收
        es.scheduleAtFixedRate(kingdomRecever, 0, 100, TimeUnit.MILLISECONDS);
        
        System.out.println("金证报盘服务启动完成...");
    }
    public void shutdown(){
        System.out.println("关闭金证报盘服务...");
        es.shutdown();
        kingdomRecever.clearResource();
        kingdomSender.clearResource();
        System.out.println("关闭金证报盘服务完成...");
    }
        
}
