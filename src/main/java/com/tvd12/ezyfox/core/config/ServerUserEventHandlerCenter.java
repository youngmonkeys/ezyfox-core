package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;

import lombok.Getter;

/**
 * Support to load and hold all structures of server user event handler's classes
 * 
 * @author tavandung12
 *
 */
public class ServerUserEventHandlerCenter {
    
    // structures of user server event handler's classes
    @Getter
    protected List<ServerUserHandlerClass> handlers = new ArrayList<>();
    
    /**
     * Add a structure of server user event handler's class to list of structures and re-sort the list
     * 
     * @param handler structure of user server handler's class
     */
    public void addHandler(ServerUserHandlerClass handler) {
        handlers.add(handler);
        Collections.sort(handlers, getComparator());
    }
    
    /**
     * Read all server user event handler's classes and create their structures and add to structures list
     * 
     * @param handlerClasses server user event handler's classes
     * @param userClass user agent's in all handler's classes
     * @param gameUserClasses game user agent's classes in all handler's classes
     * @return structures list after add elements
     */
    public List<ServerUserHandlerClass> addHandlers(List<Class<?>> handlerClasses, 
            Class<?> userClass, List<Class<?>> gameUserClasses) {
        for(Class<?> clazz : handlerClasses) {
            ServerUserHandlerClass handler = 
                    new ServerUserHandlerClass(clazz, userClass, gameUserClasses);
            addHandler(handler);
        }
        return handlers;
    }
    
    /**
     * Create a comparator to sort list of structures
     * 
     * @return a comparator
     */
    protected Comparator<ServerUserHandlerClass> getComparator() {
        return new Comparator<ServerUserHandlerClass>() {
            @Override
            public int compare(ServerUserHandlerClass c1, ServerUserHandlerClass c2) {
                return c1.getPriority()
                        - c2.getPriority();
            }
        };
    }
}
