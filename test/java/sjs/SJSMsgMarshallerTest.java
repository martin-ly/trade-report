/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.sjs;

import com.citics.repo.reporter.KeyValueMsg;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author luoguoliang
 *
 */
public class SJSMsgMarshallerTest {
    @Test
    public void sjsMsgMarsha(){
        SJSMsg sjswtMsg = new SJSMsg("SJSWT");
        sjswtMsg.set("WTHTXH","00100");//合同序号
        sjswtMsg.set("WTZQDM","00101");//证券代码
        sjswtMsg.set("WTGDDM","00102");//股东帐号
        sjswtMsg.set("WTWTSL","1");//委托数量
        String expected = "WTHTXH=00100"+KeyValueMsg.DEFAULT_SEPARATOR+"WTZQDM=00101"+KeyValueMsg.DEFAULT_SEPARATOR+"WTGDDM=00102"+KeyValueMsg.DEFAULT_SEPARATOR+"WTWTSL=1"+KeyValueMsg.DEFAULT_SEPARATOR;
        String actual = sjswtMsg.marsha();
        
        assertEquals("SJSWT", sjswtMsg.getDBFType());
        assertEquals(expected, actual);
        
        SJSMsg sjshbMsg = new SJSMsg("SJSHB");
        sjshbMsg.set("HBCJHM","00100");//成交号码
        sjshbMsg.set("HBZQDM","00101");//证券代码
        sjshbMsg.set("HBHTXH","00102");//合同序号
        sjshbMsg.set("HBGDDM","00103");//证券帐户
        String expected1 = "HBCJHM=00100"+KeyValueMsg.DEFAULT_SEPARATOR+"HBZQDM=00101"+KeyValueMsg.DEFAULT_SEPARATOR+"HBHTXH=00102"+KeyValueMsg.DEFAULT_SEPARATOR+"HBGDDM=00103"+KeyValueMsg.DEFAULT_SEPARATOR;
        String actual1 = sjshbMsg.marsha();
        
        assertEquals("SJSHB", sjshbMsg.getDBFType());
        assertEquals(expected1, actual1);
    }
    @Test
    public void sjsMsgUnMarsha(){
        String msg = "WTHTXH=00100"+KeyValueMsg.DEFAULT_SEPARATOR+"WTZQDM=00101"+KeyValueMsg.DEFAULT_SEPARATOR+"WTGDDM=00102"+KeyValueMsg.DEFAULT_SEPARATOR+"WTWTSL=1"+KeyValueMsg.DEFAULT_SEPARATOR;;
        SJSMsg sjsMsg = SJSMsg.build("SJSWT",msg);
        assertEquals("00100", sjsMsg.get("WTHTXH"));
        assertEquals("00101", sjsMsg.get("WTZQDM"));
        assertEquals("00102", sjsMsg.get("WTGDDM"));
        assertEquals("1", sjsMsg.get("WTWTSL"));
        
        assertEquals("SJSWT", sjsMsg.getDBFType());
    }
    
}
