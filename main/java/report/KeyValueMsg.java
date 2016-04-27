/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author luoguoliang
 *
 */
public class KeyValueMsg {
    public static final char DEFAULT_SEPARATOR = 1;
    private char customSeparator;
    private boolean custom = false;
    protected Map dataContainer = new LinkedHashMap();
    public KeyValueMsg() {
    }
    public KeyValueMsg(char customSeparator) {
        this.customSeparator = customSeparator;
        this.custom = true;
    }
    public static KeyValueMsg parse(String msgStr) {
        KeyValueMsg kv = new KeyValueMsg();
        kv.dataContainer.putAll(innerParse(msgStr,DEFAULT_SEPARATOR));
        return kv;
    }
    public static KeyValueMsg parse(String msgStr, char sepa) {
        KeyValueMsg kv = new KeyValueMsg(sepa);
        kv.dataContainer.putAll(innerParse(msgStr,sepa));
        return kv;
    }
    
    protected static Map innerParse(String msg,char separator){
        Map result = new LinkedHashMap();
        String[] arr = msg.split(String.valueOf(separator));
        for (String str : arr) {
            int index = str.indexOf("=");
            String key = str.substring(0, index);
            String value = str.substring(index + 1);
            result.put(key, value);
        }
        return result;
    }

    public void set(String key, String value) {
        dataContainer.put(key, value);
    }
    public String get(String key) {
        return (String) dataContainer.get(key);
    }
    public String marsha() {
        char sepa = custom?customSeparator:DEFAULT_SEPARATOR;
        StringBuffer result = new StringBuffer();
        Set set = dataContainer.entrySet();
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            Entry entry = (Entry) iter.next();
            result.append(entry.getKey() + "=" + entry.getValue() + sepa);
        }
        return result.toString();
    }

}
