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
public class EzyBadRequestException extends RuntimeException {
	private static final long serialVersionUID = 2045148125148130757L;
	
	@Getter
    private int code;
    @Getter
    private String reason = "unknown";
    @Getter
    private boolean sendToClient = true;
    
    public EzyBadRequestException() {
        super();
        this.sendToClient = false;
    }
    
    public EzyBadRequestException(String msg) {
        this(msg, 0, false);
    }
    
    public EzyBadRequestException(String msg, Throwable e) {
        this(msg, 0, false, e);
    }
    
    public EzyBadRequestException(int code) {
        this(code, true);
    }
    
    public EzyBadRequestException(String msg, int code) {
        this(msg, code, true);
    }
    
    public EzyBadRequestException(int code, String msg) {
    		this(msg, code);
    }
    
    public EzyBadRequestException(String msg, int code, Throwable e) {
        this(msg, code, true, e);
    }
    
    public EzyBadRequestException(int code, String msg, Throwable e) {
    		this(msg, code, e);
    }
    
    public EzyBadRequestException(int code, boolean sendToClient) {
        super();
        this.code = code;
        this.sendToClient = sendToClient;
    }
    
    public EzyBadRequestException(int code, boolean sendToClient, Throwable e) {
        super(e);
        this.code = code;
        this.sendToClient = sendToClient;
    }
    
    public EzyBadRequestException(int code, String msg, boolean sendToClient) {
    		this(msg, code, sendToClient);
    }
    
    public EzyBadRequestException(String msg, int code, boolean sendToClient) {
        super(msg);
        this.code = code;
        this.reason = msg;
        this.sendToClient = sendToClient;
    }
    
    public EzyBadRequestException(int code, String msg, boolean sendToClient, Throwable e) {
    		this(msg, code, sendToClient, e);
    }
    
    public EzyBadRequestException(String msg, int code, boolean sendToClient, Throwable e) {
        super(msg, e);
        this.code = code;
        this.reason = msg;
        this.sendToClient = sendToClient;
    }

}
