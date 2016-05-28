package com.tvd12.ezyfox.core.annotation.parser;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;

/**
 * Support to read {@code @ServerEventHandler} annotation
 * 
 * @author tavandung12
 *
 */

public final class ServerEventParser {

    //prevent new instance
	private ServerEventParser() {}
	
	/**
	 * Obtain event name
	 * 
	 * @param handler server event handler class
	 * @return event name
	 */
	public static String getEventName(Class<?> handler) {
		return handler
				.getAnnotation(ServerEventHandler.class)
				.event().trim();
	}
	
	/**
	 * Obtain priority of handler
	 * 
	 * @param handler handler class
	 * @return priority as int
	 */
	public static int getEventPriority(Class<?> handler) {
        return handler
                .getAnnotation(ServerEventHandler.class)
                .priority();
    }
	
}
