/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import com.citics.platformx.sdk.util.DateUtil;

import java.util.Date;

/**
 * @author luoguoliang
 *
 */
public class SessionHeader {
    public final static int SYNC_HEAD=23476; 
    
//    private int msgFormat;
    
    private byte[] data = new byte[32];

    private SessionHeader(int msgType, int msgFormat, int bodySize) {
//        this.msgFormat = msgFormat;
        buildData(msgType, msgFormat, bodySize);
    }
    private SessionHeader(byte[] msgData){
        System.arraycopy(msgData, 0, data, 0, 32);
    }
    
    public static SessionHeader build(int msgType, int msgFormat, int bodySize){
        return new SessionHeader(msgType,msgFormat, bodySize);
    }
    public static SessionHeader build(byte[] msgData){
        return new SessionHeader(msgData);
    }

    /**
     * @param msgFormat
     * @param bodySize
     */
    private void buildData(int msgType, int msgFormat, int bodySize) {
        //同步头Int16
        setByteArrayOfValueGtByte(data,0,16,SYNC_HEAD);
        //报文长度Int16
        int length = 32 + 64 + bodySize;
        setByteArrayOfValueGtByte(data,2,16,length);
        //报文校验和Int16
        data[4] = 0x00;
        data[5] = 0x00;
        //报文协议Int4 固定为1 报文版本Int4 初始版本为 1
        setInt4(data,6,(byte)1,(byte)1);
        //报文类型 Int4 0系统报文，1业务报文  消息类型 Int4 1请求消息，2应答消息
        setInt4(data,7,(byte)msgType,(byte)1);
        //时间戳 Int64 2120142101121212001
        Long timestamp = generateTimestamp();
        setByteArrayOfValueGtByte(data,8,64,timestamp);
        //业务数据格式 Int4 5.带业务头的业务接口原始格式 6.带业务头的Key/Value格式    保留字段1 Int4
        setInt4(data,16,(byte)msgFormat,(byte)0);
        //保留字段3-15
        for(int i=0;i<15;i++){
            data[17+i] = 0x00;
        }
        
    }
    //格式为CCYYMMDDhhmmssSSS。CC为世纪，YY为年，MM为月，DD为日，hh为时（24小时制），mm为分，ss为秒，SSS为毫秒
    private Long generateTimestamp() {
        String date = DateUtil.toString(new Date(), "yyyyMMddHHmmssSSS");
        int cc = Integer.parseInt(date.substring(0, 2))+ 1;
        date += String.valueOf(cc);
        Long timestamp =Long.parseLong(date);
        return timestamp;
    }
    
    private void setByteArrayOfValueGtByte(byte[] data,int startIndex,int bitNum,long value){
        int byteNum = bitNum/8;
        for(int i=byteNum-1;i<0;i--){
            byte b = (byte) (value>>((byteNum-i-1)*8));
            data[startIndex+i] = (byte) (b & 0xff);
        }
    }
    
    private void setInt4(byte[] data,int startIndex,byte lowValue,byte highValue){
        data[startIndex] = (byte) ((highValue<<4)|lowValue);
    }
    
    public byte getMsgType() {
        StringBuffer sb = new StringBuffer();
        String binaryStr = Integer.toBinaryString(data[7]);
        int len = binaryStr.length();
        for(int i=0,j=len-1;i<4&&j>=0;i++,j--){
            sb.append(binaryStr.charAt(j));
        }
        binaryStr = sb.reverse().toString();
        return (byte)Integer.parseInt(binaryStr, 2);
//        return (byte) (data[7]<<4);//求低4位要左移4位将高4位移除
        
    }

    public byte getMsgFormat() {
        StringBuffer sb = new StringBuffer();
        String binaryStr = Integer.toBinaryString(data[16]);
        int len = binaryStr.length();
        for(int i=0,j=len-1;i<4&&j>=0;i++,j--){
            sb.append(binaryStr.charAt(j));
        }
        binaryStr = sb.reverse().toString();
        return (byte)Integer.parseInt(binaryStr, 2);
//        return (byte) (data[16]<<4);//求低4位要左移4位将高4位移除
    }
    
    public int getSyncHead(){
        return getIntValueFromTwoByte(0,1);
    }
    public int getMsgLen(){
        return getIntValueFromTwoByte(2,3);
    }
    private int getIntValueFromTwoByte(int lowIndex,int highIndex) {
        String low=Integer.toHexString((int)data[lowIndex]);
        String high=Integer.toHexString((int)data[highIndex]);
        low=low.substring(low.length()-2, low.length());
        Integer t=Integer.decode("0x"+high+low);
        return t;
    }

    public byte[] getByteArray() {
        return data;
    }
    
    public static void main(String[] args) {
//        int x = 0x01;
//        int y = x<<4;
//        int z = y | 0x01;
//        int w = 0;
//        
//        
//        byte[] data = new byte[2];
//        data[0] = -76;
//        data[1] = 91;
//        System.out.println(Integer.toBinaryString(23476));
//        System.out.println(Integer.toBinaryString(data[1]));
//        System.out.println(data[1]<<8);
//        System.out.println(Integer.toBinaryString(data[1]<<8));
//        System.out.println(Integer.toBinaryString(data[0]));
//        System.out.println(Integer.toHexString(data[0]));
//        System.out.println(data[0]>>>8);//无符号右移
//        System.out.println(Integer.toBinaryString(data[0]>>>8));
//        System.out.println(Integer.valueOf("101101110110100", 2));
//        System.out.println(data[1]<<8 | data[0]>>>8);
        
        StringBuffer sb = new StringBuffer();
        String binaryStr = Integer.toBinaryString(0);
        int len = binaryStr.length();
        int i=0;
        int j = len - 1;
        while(i<4&&j>=0){
            sb.append(binaryStr.charAt(j));
            i++;
            j--;
        }
        binaryStr = sb.reverse().toString();
        
          System.out.println(Integer.parseInt(binaryStr, 2));
    }

}
