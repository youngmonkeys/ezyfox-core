package com.tvd12.ezyfox.core.bridge;

public interface ClientRequestHandlers {

	/**
	 * Get the request handler mapped to the command
	 * 
	 * @param cmd the command
	 * @return the handler
	 */
	Object getClientRequestHandler(Object cmd);
	
	/**
	 * Check whether has handler mapped to command
	 * 
	 * @param cmd the command
	 * @return true or false
	 */
	boolean containsClientRequestHandler(Object cmd);
	
}
