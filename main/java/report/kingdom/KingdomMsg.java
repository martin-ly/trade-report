/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

import com.citics.repo.reporter.KeyValueMsg;



/**
 * @author luoguoliang
 *
 */
public class KingdomMsg {
    private SessionHeader sessionHeader;
    private BusiHead head;
    private byte[] oriMsgData;
    
    protected KingdomMsg(){}
    
    private KingdomMsg(KingdomSpec spec, byte[] msgData) {
        this.oriMsgData = msgData;
        this.sessionHeader = SessionHeader.build(spec.getMsgType(),spec.getMsgFormat(),msgData.length);
        this.head = BusiHead.build(spec.getMarket(),spec.getInterface1());
    }
    private KingdomMsg(byte[] sessionHeaderData,char[] headData,byte[] msgData){
        this.oriMsgData = msgData;
        this.sessionHeader = SessionHeader.build(sessionHeaderData);
        if(headData != null){
            this.head = BusiHead.build(headData);
        }
    }
    
    private KingdomMsg(SessionHeader sessionHeader,byte[] msgData){
        oriMsgData = msgData;
        this.sessionHeader = sessionHeader;
    }
    
    public static KingdomMsg build(KingdomSpec spec, byte[] msgData){
        return new KingdomMsg(spec,msgData);
    }
    
    public static KingdomMsg build(byte[] sessionHeaderData,char[] headData,byte[] msgData){
        return new KingdomMsg(sessionHeaderData,headData,msgData);
    }
    
    public static KingdomMsg buildHeartBeatMsg() {
        KeyValueMsg kv = new KeyValueMsg();
        kv.set("BizType", "1");
        kv.set("Function", "1");
        String msgStr = kv.marsha();
        SessionHeader sessionHeader = SessionHeader.build(0, 6, msgStr.getBytes().length);
        return new KingdomMsg(sessionHeader,msgStr.getBytes());
    }

    public SessionHeader getSessionHeader() {
        return sessionHeader;
    }

    public BusiHead getHead() {
        return head;
    }

    public byte[] getEncodedData() {
        byte[] sessionHeadData = sessionHeader.getByteArray();
        
        byte[] result = null;
        if(head!=null){
            byte[] busiHeadData = converToByteArray(head.getCharArray());
            result = new byte[sessionHeadData.length + busiHeadData.length + oriMsgData.length];
            System.arraycopy(sessionHeadData, 0, result, 0, sessionHeadData.length);
            System.arraycopy(busiHeadData, 0, result, sessionHeadData.length, busiHeadData.length);
            System.arraycopy(oriMsgData, 0, result, sessionHeadData.length+busiHeadData.length, oriMsgData.length);
        }else {
            result = new byte[sessionHeadData.length + oriMsgData.length];
            System.arraycopy(sessionHeadData, 0, result, 0, sessionHeadData.length);
            System.arraycopy(oriMsgData, 0, result, sessionHeadData.length, oriMsgData.length);
        }
        
        return result;
    }
    
    public byte[] getOriMsgData(){
        byte[] data = new byte[oriMsgData.length];
        System.arraycopy(oriMsgData, 0, data, 0, oriMsgData.length);
        return data;
    }

    private byte[] converToByteArray(char[] chars) {
        String s = new String(chars);
        return s.getBytes();
    }
    
}
