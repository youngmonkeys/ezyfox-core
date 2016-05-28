/**
 * 
 */
package com.tvd12.ezyfox.core.exception;

/**
 * Throw this exception when user has a bad request or user didn't pass validation
 * 
 * @author tavandung12
 *
 */
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public BadRequestException() {
        super();
    }
    
    public BadRequestException(String msg) {
        super(msg);
    }
    
    public BadRequestException(String msg, Throwable e) {
        super(msg, e);
    }

}
