/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author luoguoliang
 * 加密核验工具类
 */
public class Sign {
    private static MessageDigest md5 = null;
    private static Logger log = Logger.getLogger(Sign.class);
    
    static{
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("初始化MD5失败", e);
            throw new RuntimeException("初始化MD5失败", e);
        }
    }
    /**
     * 加签
     * @param plainText
     * @return
     */
    public static String sign(String plainText) {
        try {
            byte[] data = plainText.getBytes("UTF-8");
            md5.update(data);
            byte[] md5Bytes = md5.digest();
            return Base64.encodeBase64String(md5Bytes);
        } catch (UnsupportedEncodingException e) {
            log.error("计算签名错误 ，明文：" + plainText, e);
            throw new RuntimeException("计算签名错误 ，明文：" + plainText, e);
        } 
    }
    /**
     * 核验
     * @param signStr
     * @param plainText
     * @return
     */
    public static boolean veryfy(String signStr,String plainText){
        try {
            md5.update(plainText.getBytes("UTF-8"));
            byte[] md5Bytes = md5.digest();
            String encodeStr = Base64.encodeBase64String(md5Bytes);
            return signStr.equals(encodeStr);
        } catch (UnsupportedEncodingException e) {
            log.error("报文校验出错,明文:" + plainText + ",加签串:"+signStr, e);
            throw new RuntimeException("报文校验出错,明文:" + plainText + ",加签串:"+signStr, e);
        }
    }
}
