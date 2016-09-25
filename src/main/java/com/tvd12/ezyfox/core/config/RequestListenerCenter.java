package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.structure.RequestResponseClass;

/**
 * 
 * Support to load and hold all client request commands and listener classes
 * 
 * @author tavandung12
 *
 */
public class RequestListenerCenter {
	
    /**
     * map of commands and structures of listener classes
     */
    protected Map<String, List<RequestResponseClass>> listeners =
            new HashMap<>();
    
    /**
     * add command and structure of listener class to map
     * 
     * @param command client request listener's command
     * @param listener structure of listener class
     */
    public void addListener(String command, RequestResponseClass listener) {
        if(listeners.containsKey(command))
            addListenerByPriority(command, listener);
        else 
            listeners.put(command, asList(listener));
    }
    
    /**
     * add command and structure of listener class to map and sort all by priority, lowest is first
     * 
     * @param command request listener's command
     * @param listener structure of listener class
     */
    protected void addListenerByPriority(String command, RequestResponseClass listener) {
        List<RequestResponseClass> list = listeners.get(command);
        list.add(listener);
        Collections.sort(list, getComparator());
    }
    
    /**
     * get all request listener's classes related to command
     * 
     * @param command request listener's command
     * @return structures of request listener's classes
     */
    public List<RequestResponseClass> getListeners(String command) {
        return (listeners.containsKey(command)) 
                ? listeners.get(command) : new ArrayList<RequestResponseClass>();
    }
    
    /**
     * add all structures of request listener's classes, sort them and add to map
     * 
     * @param listeners structures of request listener's to add
     */
    public void addListeners(List<RequestResponseClass> listeners) {
        for(RequestResponseClass listener : listeners) {
            String command = getCommand(listener);
            addListener(command, listener);
        }
    }
    
    /**
     * get all request commands
     * 
     * @return the set of commands
     */
    public Set<String> getCommands() {
        return listeners.keySet();
    }
    
    /**
     * convert array of RequestResponseClass objects to list
     * 
     * @param classes RequestResponseClass objects
     * @return list of RequestResponseClass objects
     */
    private List<RequestResponseClass> asList(RequestResponseClass...classes) {
        List<RequestResponseClass> list = new ArrayList<>();
        for(RequestResponseClass clazz : classes) {
            list.add(clazz);
        }
        return list;
    }
    
    /**
     * get command of request listener's class
     * 
     * @param clazz structure of request listener's class
     * @return command name
     */
	protected String getCommand(RequestResponseClass clazz) {
		return clazz.getRequestCommand();
	}

	/**
	 * create a comparator to sort listener's class
	 * 
	 * @return a comparator
	 */
	protected Comparator<RequestResponseClass> getComparator() {
		return new Comparator<RequestResponseClass>() {
			@Override
			public int compare(RequestResponseClass c1, RequestResponseClass c2) {
				return c1.getPriority()
						- c2.getPriority();
			}
		};
	}
	
}
