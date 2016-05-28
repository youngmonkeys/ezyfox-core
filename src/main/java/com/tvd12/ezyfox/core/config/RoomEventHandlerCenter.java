package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tvd12.ezyfox.core.structure.RoomHandlerClass;

import lombok.Getter;

/**
 * 
 * Support to load and hold all structures of room event handler's classes
 * 
 * @author tavandung12
 *
 */
public class RoomEventHandlerCenter {

    // structures of room event handler's classes
    @Getter
    protected List<RoomHandlerClass> handlers = new ArrayList<>();
    
    /**
     * Add a structure of room event handler's class to list of structures and re-sort the list
     * 
     * @param handler structure of room handler's class
     */
    public void addHandler(RoomHandlerClass handler) {
        handlers.add(handler);
        Collections.sort(handlers, getComparator());
    }
    
    /**
     * Read all room event handler's classes and create their structures and add to structures list
     * 
     * @param handlerClasses room event handler's classes
     * @param roomClasses list of room classes in all handler's classes
     * @param userClass user agent's in all handler's classes
     * @param gameUserClasses game user agent's classes in all handler's classes
     * @return structures list after add elements
     */
    public List<RoomHandlerClass> addHandlers(List<Class<?>> handlerClasses, 
            List<Class<?>> roomClasses, 
            Class<?> userClass, List<Class<?>> gameUserClasses) {
        for(Class<?> clazz : handlerClasses) {
            RoomHandlerClass handler = new RoomHandlerClass(
                    clazz, roomClasses, userClass, gameUserClasses);
            addHandler(handler);
        }
        return handlers;
    }
    
    /**
     * Create a comparator to sort list of structures
     * 
     * @return a comparator
     */
    protected Comparator<RoomHandlerClass> getComparator() {
        return new Comparator<RoomHandlerClass>() {
            @Override
            public int compare(RoomHandlerClass c1, RoomHandlerClass c2) {
                return c1.getPriority()
                        - c2.getPriority();
            }
        };
    }
    
}
