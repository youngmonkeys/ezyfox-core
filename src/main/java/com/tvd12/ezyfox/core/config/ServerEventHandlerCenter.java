package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tvd12.ezyfox.core.structure.ServerHandlerClass;

/**
 * Support to load and hold all structures of server event handler's classes
 * 
 * @author tavandung12
 *
 */

public class ServerEventHandlerCenter {

    /**
     * Create new handler instance
     * 
     * @param clazz handler class
     * @param paramTypes parameter types of handle method
     * @return a handler instance
     */
    @SuppressWarnings("unchecked")
    protected <T extends ServerHandlerClass> T newHandler(Class<?> clazz,
            Class<?>... paramTypes) {
        return (T) new ServerHandlerClass(clazz, paramTypes);
    }
    
    /**
     * Add a structure of server event handler's class to list of structures and re-sort the list
     * 
     * @param handlers list of handler
     * @param handler handler
     */
    public <T extends ServerHandlerClass> void addHandler(List<T> handlers, T handler) {
        handlers.add(handler);
        Collections.sort(handlers, getComparator());
    }
    
    /**
     * Read all server event handler's classes and create their structures and add to structures list
     *
     * @param classes room event handler's classes
     * @param paramTypes array of parameter types of handle method in handler's class
     */
    @SuppressWarnings("unchecked")
    public <T extends ServerHandlerClass> List<T> addHandlers(List<Class<?>> classes,
            Class<?>... paramTypes) {
        List<T> handlers = new ArrayList<>();
        for(Class<?> clazz : classes) {
            addHandler(handlers, (T)newHandler(clazz, paramTypes));
        }
        return handlers;
    }

    /**
     * 
     * Create a comparator to sort list of structures
     * 
     * @return a comparator
     */
    protected Comparator<ServerHandlerClass> getComparator() {
        return new Comparator<ServerHandlerClass>() {
            @Override
            public int compare(ServerHandlerClass c1, ServerHandlerClass c2) {
                return c1.getPriority()
                        - c2.getPriority();
            }
        };
    }
    
}
