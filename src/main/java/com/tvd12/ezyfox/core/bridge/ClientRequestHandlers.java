package com.tvd12.ezyfox.core.bridge;

public interface ClientRequestHandlers {

	/**
	 * Get the request handler mapped to the command
	 * 
	 * @param cmd the command
	 */
	void getClientRequestHandler(Object cmd);
	
}
