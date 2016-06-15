/**
 * 
 */
package com.tvd12.ezyfox.core.exception;

import lombok.Getter;

/**
 * Throw this exception when user has a bad request or user didn't pass validation
 * 
 * @author tavandung12
 *
 */
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;
    
    @Getter
    private int code;
    @Getter
    private String reason = "unknown";
    @Getter
    private boolean sendToClient = true;
    
    public BadRequestException() {
        super();
        sendToClient = false;
    }
    
    public BadRequestException(String msg) {
        this(msg, 0, false);
    }
    
    public BadRequestException(String msg, Throwable e) {
        this(msg, 0, false, e);
    }
    
    public BadRequestException(int code) {
        this(code, true);
    }
    
    public BadRequestException(String msg, int code) {
        this(msg, code, true);
    }
    
    public BadRequestException(String msg, int code, Throwable e) {
        this(msg, code, true, e);
    }
    
    public BadRequestException(int code, boolean sendToClient) {
        super();
        this.code = code;
        this.sendToClient = sendToClient;
    }
    
    public BadRequestException(int code, boolean sendToClient, Throwable e) {
        super(e);
        this.code = code;
        this.sendToClient = sendToClient;
    }
    
    public BadRequestException(String msg, int code, boolean sendToClient) {
        super(msg);
        this.code = code;
        this.reason = msg;
        this.sendToClient = sendToClient;
    }
    
    public BadRequestException(String msg, int code, boolean sendToClient, Throwable e) {
        super(msg, e);
        this.code = code;
        this.reason = msg;
        this.sendToClient = sendToClient;
    }

}
