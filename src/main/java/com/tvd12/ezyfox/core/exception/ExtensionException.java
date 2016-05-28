package com.tvd12.ezyfox.core.exception;

/**
 * Throw this exception when has any errors in extension application
 * 
 * @author tavandung12
 *
 */

public class ExtensionException extends Exception {
	private static final long serialVersionUID = 4397438044599692798L;

	public ExtensionException() {
		super();
	}
	
	public ExtensionException(String msg) {
		super(msg);
	}
	
	public ExtensionException(String msg, Throwable e) {
		super(msg, e);
	}
}
