package com.tvd12.ezyfox.core.exception;

/**
 * Throw this exception when get any errors while parsing annotation
 * 
 * @author tavandung12
 *
 */

public class AnnotationException extends Exception {
	private static final long serialVersionUID = -4815429480957702219L;

	public AnnotationException() {
		super();
	}
	
	public AnnotationException(String msg) {
		super(msg);
	}
	
	public AnnotationException(String msg, Throwable e) {
		super(msg, e);
	}
}
