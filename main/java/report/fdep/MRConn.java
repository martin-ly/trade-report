/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import com.sscc.fdep.mrapi;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author luoguoliang
 * 封装FDEPAPI的对象
 *
 */
public class MRConn {
    private static MRConn instance;
    private String appID;
    private String appPWD;
    private String userID;
    private String[] mrIps;
    
    private MRConn() {
        _init();
    }
    
    private void _init(){
        try {
            readConfig();
            int initVal = mrapi.MrInit(appID, appPWD, mrIps[0], Short.parseShort(mrIps[1]), mrIps[2], Short.parseShort(mrIps[3]));
            if(initVal!=1){
                throw new RuntimeException("MR初始化失败");
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
            throw new RuntimeException("读取配置文件失败",e);
        }
    }
    private void readConfig() throws ConfigurationException {
        Configuration config = new PropertiesConfiguration("mr.properties");
        userID = config.getString("userID");
        appID = config.getString("appID");
        appPWD = config.getString("appPWD");
        mrIps = config.getStringArray("mrIps");
    }
    public static MRConn init(){
        if(instance==null){
            synchronized (MRConn.class) {
                if (instance==null) {
                    instance = new MRConn();
                }
            }
        }
        return instance;
    }
    
    public String sendMsg(String msg,String destUserID,String destAppID,String corrPKGID){
        String pkgID = new String(mrapi.Mr2CreatePkgID(appID));
        return mrapi.MrSend(msg.getBytes(), userID, appID, destUserID, destAppID, pkgID, corrPKGID, "", "", "", (byte)0, (byte)0, 2000);
    }
    
    public String recvMsg(long waitMilliTime){
        byte[] recvData = mrapi.Mr2Receive3(appID, "", "", "", "", "", "", "", "", (int)waitMilliTime);
        return new String(recvData);
    }
}
