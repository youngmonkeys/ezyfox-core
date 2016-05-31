package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;

import lombok.Getter;

/**
 * Support to hold structure of server event handler class
 * 
 * @author tavandung12
 *
 */

public class ServerHandlerClass {
    
    // priority of handler class
    @Getter
    protected int priority;
    
    // server event name
    @Getter
    protected String eventName;
    
    // handle method
    @Getter
    protected Method handleMethod;
    
    // handler class
    protected Class<?> clazz;
    
 // which object holds structure of that methods be annotated with PropertyConfig annotation
    @Getter
    protected PropertiesClassWrapper propertiesClassWrapper;
    
    /**
     * Construct with handler class and parameter types of handle method
     * 
     * @param clazz handler class
     * @param paramTypes parameter types of handle method
     */
    public ServerHandlerClass(Class<?> clazz, Class<?>... paramTypes) {
        this.clazz = clazz;
        init(clazz, paramTypes);
        checkPriority(clazz);
        checkEventName(clazz);
        checkPropertiesClass(clazz);
    }
    
    /**
     * Initialize with handler class and parameter types of handle method
     * 
     * @param clazz handler class
     * @param paramTypes parameter types of handle method
     */
    protected void init(Class<?> clazz, Class<?>... paramTypes) {
        checkHandleMethod(clazz, paramTypes);
    }
    
    /**
     * Get priority of handler class and check its
     * 
     * @param clazz handler class
     */
    protected void checkPriority(Class<?> clazz) {
        priority = clazz.getAnnotation(ServerEventHandler.class)
                .priority();
    }
    
    /**
     * Get event name of handler class and check it
     * 
     * @param clazz handler class
     */
    protected void checkEventName(Class<?> clazz) {
        eventName = clazz.getAnnotation(ServerEventHandler.class)
                .event();
    }
    
    /**
     * Get handle method of handler class and check it
     * 
     * @param clazz handler class
     * @param paramTypes parameter types
     */
    private void checkHandleMethod(Class<?> clazz, Class<?>... paramTypes) {
        if(handleMethod != null)
            return;
        handleMethod = HandleMethodParser.getServerHandleMethod(
                clazz, 
                ArrayUtils.add(paramTypes, 0, AppContext.class));
    }
    
    /**
     * init and check properties class structure 
     */
    protected void checkPropertiesClass(Class<?> clazz) {
        propertiesClassWrapper = new PropertiesClassWrapper(clazz);
    }
    
    /**
     * Create a handler instance
     * 
     * @return an instance
     */
    public Object newInstance() {
        try {
            return ReflectClassUtil.newInstance(clazz);
        } catch (ExtensionException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
