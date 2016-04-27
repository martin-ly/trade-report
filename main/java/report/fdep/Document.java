/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter.fdep;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoguoliang
 *
 */
public class Document {
    
    private List msgBodyList;
    
    public Document(){
        msgBodyList = new ArrayList();
    }
    
    public void add(FDEPMsgBody msgBody) {
        msgBodyList.add(msgBody);
    }
    
    public List getMsgBodyList() {
        return msgBodyList;
    }
    
    @Override
    public boolean equals(Object obj) {
        Document document = (Document) obj;
        if (this.getMsgBodyList().size() != document.getMsgBodyList().size()) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < document.getMsgBodyList().size(); i++) {
            Object otherMsgBody = document.getMsgBodyList().get(i);
            Object myMsgBody = this.getMsgBodyList().get(i);
            result = result && myMsgBody.equals(otherMsgBody);
        }
        return result;
    }
    
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.getMsgBodyList().size(); i++) {
            Object myMsgBody = this.getMsgBodyList().get(i);
            sb.append("{");
            sb.append(myMsgBody.toString());
            sb.append("}");
        }
        return sb.toString();
    }
}
