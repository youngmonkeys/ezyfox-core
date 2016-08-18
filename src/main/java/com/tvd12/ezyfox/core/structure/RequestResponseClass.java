package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Method;
import java.util.List;

import com.tvd12.ezyfox.core.annotation.parser.ClientRequestParser;
import com.tvd12.ezyfox.core.annotation.parser.ClientResponseParser;
import com.tvd12.ezyfox.core.annotation.parser.ExecutionMethodParser;

import lombok.Getter;

/**
 * Hold structure of client request listener class
 * 
 * @see RequestListenerClass
 * @see ResponseHandlerClass
 * 
 * @author tavandung12
 *
 */

public class RequestResponseClass {

    // priority of listener class, lowest is first
    @Getter
    private int priority;
    
    // request command
    @Getter
    private String requestCommand;
    
    // response command
    @Getter
    private String responseCommand;
    
    // response to client?
    @Getter
    private boolean responseToClient;
    
    // execute method
    @Getter
    protected Method executeMethod;
    
    // user agent class
    @Getter
    private Class<?> userClass;
    
    // structure of listener class
    @Getter
    private RequestListenerClass requestListenerClass;
    
    // structure of response handler class
    @Getter
    private ResponseHandlerClass responseHandlerClass;
    
    /**
     * Construct with java class, user agent class and list of game user agent's classes
     * 
     * @param clazz request listener's class
     * @param userClazz user agent's class
     * @param gameUserClasses game user agent's classes
     */
    public RequestResponseClass(Class<?> clazz, 
            Class<?> userClazz, List<Class<?>> gameUserClasses) {
        checkRequestCommand(clazz);
        checkResponseToClient(clazz);
        checkResponseCommand(clazz);
        checkRequestPriority(clazz);
        checkExecuteMethod(clazz, userClazz, gameUserClasses);
        userClass = fetchUserClass();
        requestListenerClass = new RequestListenerClass(clazz);
        responseHandlerClass = new ResponseHandlerClass(clazz);
    }
    
    protected Class<?> fetchUserClass() {
        return executeMethod.getParameterTypes()[1];
    }
    
    /**
     * Create an instance
     * 
     * @return an instance
     */
    public Object newInstance() {
        return requestListenerClass.newInstance();
    }
    
    /**
     * Get request command and check its
     * 
     * @param clazz request listener class
     */
    private void checkRequestCommand(Class<?> clazz) {
        requestCommand = ClientRequestParser.getCommand(clazz);
    }
    
    /**
     * Get response command and check its
     * 
     * @param clazz request listener class
     */
    private void checkResponseCommand(Class<?> clazz) {
        if(!isResponseToClient())
            return;
        responseCommand = ClientResponseParser
                .getCommand(clazz, requestCommand);
    }
    
    /**
     * Check whether {@code clazz} be annotated with {@code ClientResponseHandler} annotation
     * 
     * @param clazz
     */
    private void checkResponseToClient(Class<?> clazz) {
        responseToClient = ClientResponseParser.hasAnnotation(clazz);
    }
    
    /**
     * Get request listener class's priority and check its
     * 
     * @param clazz listener class
     */
    private void checkRequestPriority(Class<?> clazz) {
        priority = ClientRequestParser.getPriority(clazz);
    }
    
    
    protected void checkExecuteMethod(Class<?> clazz, 
            Class<?> userClazz, List<Class<?>> gameUserClasses) {
        executeMethod = ExecutionMethodParser
                .getListenerExecuteMethod(clazz, userClazz, gameUserClasses);
    }
    
}
