package com.tvd12.ezyfox.core.config;

import java.util.Comparator;

import com.tvd12.ezyfox.core.annotation.parser.ServerEventParser;

/**
 * Support to load and hold all server event handler's classes
 * 
 * @author tavandung12
 *
 */

public class ServerEventHandlerClasses extends BaseEventHandlerClasses {
	
    /**
     * Get event name related to handler class
     * 
     * @param handler handler's class
     * @return event name
     */
	@Override
	protected String getEventName(Class<?> handler) {
		return ServerEventParser.getEventName(handler);
	}

	/**
	 * Create a comparator to sort handler's classes
	 * 
	 * @return a comparator
	 */
	@Override
	protected Comparator<Class<?>> getComparator() {
		return new Comparator<Class<?>>() {
			@Override
			public int compare(Class<?> c1, Class<?> c2) {
				return ServerEventParser.getEventPriority(c1)
						- ServerEventParser.getEventPriority(c2);
			}
		};
	}
	
}
