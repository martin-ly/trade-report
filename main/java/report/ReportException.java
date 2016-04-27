/**
 * Copyright (c) 2014 Citics Inc., All Rights Reserved.
 */
package com.citics.repo.reporter;

/**
 * @author luoguoliang
 *
 */
public class ReportException extends Exception {
    

    public ReportException(Exception e) {
        super(e);
    }
    
    public ReportException(String message,Exception e) {
        super(message,e);
    }
    
}
