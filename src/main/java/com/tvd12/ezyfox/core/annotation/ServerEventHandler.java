package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Indicates that a class handle a server event
 * 
 * @author tavandung12
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE })
public @interface ServerEventHandler {
    
    /**
     * server event name
     * 
     * @return server event name
     */
	public String event();
	
	/**
	 * priority of handler class, lowest is first
	 * 
	 * @return priority of handler class
	 */
	public int priority() default 0;
}
