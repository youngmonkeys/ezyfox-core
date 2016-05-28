package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tvd12.ezyfox.core.structure.ServerHandlerClass;

import lombok.Getter;

/**
 * Support to load and hold all structures of server event handler's classes
 * 
 * @author tavandung12
 *
 */

public class ServerEventHandlerCenter {

    // structures of server event handler's classes
    @Getter
    protected List<ServerHandlerClass> handlers = new ArrayList<>();
    
    /**
     * Add a structure of server event handler's class to list of structures and re-sort the list
     * 
     * @param handler
     */
    public void addListener(ServerHandlerClass handler) {
        handlers.add(handler);
        Collections.sort(handlers, getComparator());
    }
    
    /**
     * Read all server event handler's classes and create their structures and add to structures list
     *
     * @param classes room event handler's classes
     * @param paramTypes array of parameter types of handle method in handler's class
     */
    public List<ServerHandlerClass> addListeners(List<Class<?>> classes,
            Class<?>... paramTypes) {
        for(Class<?> clazz : classes) {
            addListener(new ServerHandlerClass(clazz, paramTypes));
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
