package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * Support to manage all handler classes that handle server events
 * 
 * @author tavandung12
 *
 */
public abstract class BaseEventHandlerClasses {
    
    // server event handlers
	protected Map<String, List<Class<?>>> handlers =
			new HashMap<>();

	/**
	 * Add a handler class related to event to map
	 * 
	 * @param event event name
	 * @param handler handler class
	 */
	public void addHandler(String event, Class<?> handler) {
		if(handlers.containsKey(event))
			addHandlerByPriority(event, handler);
		else 
			handlers.put(event, asList(handler));
	}
	
	/**
	 * Add a handler class related to event to map and sort list of handler classes by priority
	 * 
	 * @param event event name
	 * @param handler handler class
	 */
	protected void addHandlerByPriority(String event, Class<?> handler) {
		List<Class<?>> list = handlers.get(event);
		list.add(handler);
		Collections.sort(list, getComparator());
	}
	
	/**
	 * Obtain comparator to sort handler classes by priority
	 * 
	 * @return the comparator object
	 */
	protected abstract Comparator<Class<?>> getComparator();
	
	/**
	 * Obtain event of handler class
	 * 
	 * @param clazz handler class
	 * @return event name of handler class
	 */
	protected abstract String getEventName(Class<?> clazz);
	
	public List<Class<?>> getHandlers(String event) {
	    if (handlers.containsKey(event))
            return handlers.get(event);
        else
            return new ArrayList<>();
	}
	
	/**
	 * Add list of handlers class to map
	 * 
	 * @param handlers set of handler classes
	 */
	public void addHandlers(Set<Class<?>> handlers) {
		for(Class<?> handler : handlers) {
			String command = getEventName(handler);
			addHandler(command, handler);
		}
	}
	
	/**
	 * @return set of event names
	 */
	public Set<String> getEvents() {
		return handlers.keySet();
	}
	
	/**
	 * Convert array of classes to list
	 * 
	 * @param classes array of classes
	 * @return lis of classes
	 */
	private List<Class<?>> asList(Class<?>...classes) {
		List<Class<?>> list = new ArrayList<>();
		for(Class<?> clazz : classes) {
			list.add(clazz);
		}
		return list;
	}
}
