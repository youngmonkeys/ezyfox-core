package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tvd12.ezyfox.core.structure.ZoneHandlerClass;

import lombok.Getter;
/**
 * 
 * Support to load and hold all structures of zone event handler's classes
 * 
 * @author tavandung12
 *
 */
public class ZoneEventHandlerCenter {

    // structures of zone event handler's classes
    @Getter
    protected List<ZoneHandlerClass> handlers = new ArrayList<>();
    
    /**
     * Add a structure of zone event handler's class to list of structures and re-sort the list
     * 
     * @param handler structure of zone handler's class
     */
    public void addHandler(ZoneHandlerClass handler) {
        handlers.add(handler);
        Collections.sort(handlers, getComparator());
    }
    
    /**
     * Read all zone event handler's classes and create their structures and add to structures list
     * 
     * @param handlerClasses zone event handler's classes
     * @param userClass user agent's in all handler's classes
     * @param gameUserClasses game user agent's classes in all handler's classes
     * @return structures list after add elements
     */
    public List<ZoneHandlerClass> addHandlers(List<Class<?>> handlerClasses, 
            Class<?> userClass, List<Class<?>> gameUserClasses) {
        for(Class<?> clazz : handlerClasses) {
            ZoneHandlerClass handler = 
                    new ZoneHandlerClass(clazz, userClass, gameUserClasses);
            addHandler(handler);
        }
        return handlers;
    }
    
    /**
     * Create a comparator to sort list of structures
     * 
     * @return a comparator
     */
    protected Comparator<ZoneHandlerClass> getComparator() {
        return new Comparator<ZoneHandlerClass>() {
            @Override
            public int compare(ZoneHandlerClass c1, ZoneHandlerClass c2) {
                return c1.getPriority()
                        - c2.getPriority();
            }
        };
    }
    
}
