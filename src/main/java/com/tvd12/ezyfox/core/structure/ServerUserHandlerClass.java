package com.tvd12.ezyfox.core.structure;

import java.util.Set;

import com.tvd12.ezyfox.core.annotation.parser.HandleMethodParser;

import lombok.Getter;

/**
 * Support to hold structure of server event handler class related to user
 * 
 * @see ServerHandlerClass
 * 
 * @author tavandung12
 *
 */

public class ServerUserHandlerClass extends ServerHandlerClass {

    // user agent's class
    @Getter
    protected Class<?> userClass;
    
    /**
     * @param clazz the class to parse
     */
    public ServerUserHandlerClass(Class<?> clazz) {
        super(clazz);
    }
    
    /**
     * Construct with handler class, user agent's class and game user agent's classes
     * 
     * @param clazz handler class
     * @param userClass user agent's class
     * @param gameUserClasses game user agent's classes
     */
    public ServerUserHandlerClass(Class<?> clazz, 
            Class<?> userClass, Set<Class<?>> gameUserClasses) {
        super(clazz);
        checkHandleMethod(clazz, userClass, gameUserClasses);
        checkUserClass();
    }
    
    /**
     * @see ServerHandlerClass#init(Class, Class...)
     */
    @Override
    protected void init(Class<?> clazz, Class<?>... classes) {
        init(clazz);
    }
    
    /**
     * Initialize with handler class
     * 
     * @param clazz handler class
     */
    protected void init(Class<?> clazz) {}

    /**
     * Get handler method and check its
     * 
     * @param clazz handler class
     * @param userClass user agent's class
     * @param gameUserClasses list of game user agent's classes
     */
    protected void checkHandleMethod(Class<?> clazz, 
            Class<?> userClass, Set<Class<?>> gameUserClasses) {
        handleMethod = HandleMethodParser.getServerHandleMethod(
                clazz, userClass, gameUserClasses);
    }
    
    /**
     * get user agent's class and check it
     */
    protected void checkUserClass() {
        userClass = handleMethod.getParameterTypes()[1];
    }
    
}
