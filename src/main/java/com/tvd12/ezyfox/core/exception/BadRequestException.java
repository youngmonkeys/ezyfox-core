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
    
    public BadRequestException() {
        super();
    }
    
    public BadRequestException(String msg) {
        super(msg);
    }
    
    public BadRequestException(String msg, Throwable e) {
        super(msg, e);
    }
    
    public BadRequestException(int code) {
        super();
        this.code = code;
    }
    
    public BadRequestException(String msg, int code) {
        super(msg);
        this.code = code;
    }
    
    public BadRequestException(String msg, int code, Throwable e) {
        super(msg, e);
        this.code = code;
    }

}
