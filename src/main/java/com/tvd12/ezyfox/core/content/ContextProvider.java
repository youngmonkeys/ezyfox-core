package com.tvd12.ezyfox.core.content;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * Support for create and save application context reference
 * 
 * @author tavandung12
 *
 */
public final class ContextProvider {

    // map of application context and its application entry point's class
	private ConcurrentMap<Class<?>, AppContext> contexts;
	
	// singleton instance
	private static final ContextProvider INSTANCE 
        = new ContextProvider();
	
	// default constructor
	private ContextProvider() {
		contexts = new ConcurrentHashMap<>();
	}
	
	// get singleton instance
	public static ContextProvider getInstance() {
	    return INSTANCE;
	}
	
	/**
	 * add a application entry point's class and application context pair to map 
	 * 
	 * @param entryPoint application entry point's class
	 * @param context application context reference
	 * @return application context added
	 */
	public AppContext addContext(Class<?> entryPoint, AppContext context) {
		AppContext available = contexts.get(entryPoint);
        if(available == null) {
            available = context;
            contexts.put(entryPoint, available);
        }
		return available;
	}
	
	/**
	 * Get application context by application entry point's class
	 * 
	 * @param entryPoint application entry point's 
	 * @return application context
	 */
	public AppContext getContext(Class<?> entryPoint) {
		AppContext context = contexts.get(entryPoint);
		if(context == null)
			throw new RuntimeException("Has no contexts with entry point = " + entryPoint);
		return context;
	}
	
}
