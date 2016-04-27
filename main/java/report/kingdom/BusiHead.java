/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.kingdom;

/**
 * @author luoguoliang
 *
 */
public class BusiHead {
    private char[] data;
    
    private BusiHead(char market,char interface1){
        buildData(market, interface1);
    }
    private BusiHead(char[] msgData){
        data = new char[64];
        System.arraycopy(msgData, 0, data, 0, data.length);
    }
    
    public static BusiHead build(char market,char interface1){
        return new BusiHead(market, interface1);
    }
    public static BusiHead build(char[] msgData){
        return new BusiHead(msgData);
    }

    /**
     * @param market
     * @param interface1
     */
    private void buildData(char market, char interface1) {
        data = new char[64];
        data[0] = '1';//业务类型1 证券报盘
        data[1] = market;//交易市场 0 深交所 1 上交所 2 北京股转 3 证金公司 4 深证通平台
        data[2] = ' ';//交易板块 0 A股 1 B股 2 三板 3 港股通 5 期权 没有合理值时填空格
        data[3] = ' ';//交易平台 对于深交所（第5版）  没有合理值时填空格
        data[4] = interface1;//交易接口
        data[5] = ' ';//报盘消息类型 没有合理值时填空格
        data[6] = ' ';//交易通道 没有合理值时填空格
        data[7] = ' ';//席位代码
        data[8] = ' ';//合同序号
        data[9] = ' ';//证券账户
        data[10] = ' ';//证券代码
        
    }

    public char getMarket() {
        return data[1];
    }

    public char getInterface() {
        return data[4];
    }
    
    public char getMsgType() {
        return data[5];
    }

    public char[] getCharArray() {
        return data;
    }
    

}
