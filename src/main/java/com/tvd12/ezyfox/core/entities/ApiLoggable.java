package com.tvd12.ezyfox.core.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiLoggable {

	private Logger logger = 
			LoggerFactory.getLogger(getClass());
	
	protected Logger getLogger() {
		return logger;
	}
	
}
