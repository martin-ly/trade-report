/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author luoguoliang
 *
 */
public class MsgHandlerFactory {
    private static Logger log = Logger.getLogger(MsgHandlerFactory.class);
    private static Map<String, String> map = new HashMap<String, String>();
    
    static{
        try {
            Configuration config = new PropertiesConfiguration("msghandler.properties");
            Iterator iterator = config.getKeys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String clazzName = config.getString(key);
                map.put(key, clazzName);
            }
        } catch (ConfigurationException e) {
            e.printStackTrace();
            log.error("读取配置文件失败", e);
            throw new RuntimeException("读取配置文件失败",e);
        }
    }

    public static MsgHandler createHandler(String sessionMsgType,String funcType,String market, String interface1, String msgType) {
        String clazzName = map.get(sessionMsgType+funcType+market+interface1+msgType);
        if(clazzName == null){
            log.error("不存在sessionMsgType="+sessionMsgType+",funcType="+funcType+",market="+market+",interface1="+interface1+",msgType="+msgType+"的处理器");
            throw new RuntimeException("不存在sessionMsgType="+sessionMsgType+",funcType="+funcType+",market="+market+",interface1="+interface1+",msgType="+msgType+"的处理器");
        }
        try {
            return (MsgHandler) Class.forName(clazzName).newInstance();
//            return new SjszhwtConfirmMsgHandler();
        } catch (Exception e) {
            log.error("实例化类'"+clazzName+"'失败", e);
            throw new RuntimeException("实例化类'"+clazzName+"'失败", e);
        } 
    }
    
}
