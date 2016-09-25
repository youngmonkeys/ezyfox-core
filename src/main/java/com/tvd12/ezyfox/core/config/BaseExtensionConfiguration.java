/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 *
 */
public class BaseExtensionConfiguration {

    // the map of classes and their serializer
    @Setter @Getter
    protected Map<Class<?>, Class<?>> objectSerializerClasses;

    // the map of classes and their desializer
    @Setter @Getter
    protected Map<Class<?>, Class<?>> objectDeserializerClasses;

    // the map of response classes and their structure
    @Getter
    protected Map<Class<?>, ResponseParamsClass> responseParamsClasses;

    // the map of message parameter classes and their structure
    @Getter
    protected Map<Class<?>, MessageParamsClass> messageParamsClasses;

    // the map of request classes and their structure
    @Getter
    protected List<RequestResponseClass> requestResponseClientClasses;

    // list of server event handler classes
    @Setter @Getter
    protected Set<Class<?>> serverEventHandlerClasses;
    
    /**
     * Set message parameter classes and parse their structure
     * 
     * @param classes the classes
     */
    public void setMessageParamsClasses(Set<Class<?>> classes) {
        this.messageParamsClasses = createMessageParamsClasses(classes);
    }
    
    /**
     * Set request listener classes and parse their structure
     * 
     * @param classes the classes
     */
    public void setRequestResponseClientClasses(Set<Class<?>> classes) {
        this.requestResponseClientClasses = createRequestResponseHandlers(classes);
    }
    
    /**
     * Set response parameter classes and parse their structure
     * 
     * @param classes the response parameter classes
     */
    public void setResponseParamsClasses(Set<Class<?>> classes) {
        this.responseParamsClasses = createResponseParamsClasses(classes);
    }
    
    /**
     * Check all components
     */
    public void checkAll() {
        unmodifyAll();
        checkRequestRequestClasses();
    }
    
    /**
     * Unmodify all component
     */
    protected void unmodifyAll() {
        this.objectSerializerClasses = Collections.unmodifiableMap(objectSerializerClasses);
        this.objectDeserializerClasses = Collections.unmodifiableMap(objectDeserializerClasses);
        this.responseParamsClasses = Collections.unmodifiableMap(responseParamsClasses);
        this.messageParamsClasses = Collections.unmodifiableMap(messageParamsClasses);
        this.serverEventHandlerClasses = Collections.unmodifiableSet(serverEventHandlerClasses);
        this.requestResponseClientClasses = Collections.unmodifiableList(requestResponseClientClasses);
    }
    
    /**
     * Check all request listener classes
     */
    protected void checkRequestRequestClasses() {
        for(RequestResponseClass clazz : requestResponseClientClasses)
            checkExecuteMethod(clazz);
    }
    
    protected Map<Class<?>, ResponseParamsClass> 
                createResponseParamsClasses(Set<Class<?>> classes) {
        Map<Class<?>, ResponseParamsClass> answer = new HashMap<>();
        for(Class<?> clazz : classes)
            answer.put(clazz, new ResponseParamsClass(clazz));
        return answer;
    }
    
    protected List<RequestResponseClass> 
                createRequestResponseHandlers(Set<Class<?>> classes) {
        List<RequestResponseClass> answer = new ArrayList<>();
        for(Class<?> clazz : classes)
            answer.add(newAndInitRequestResponseClass(clazz));
        return answer;
    }
    
    protected Map<Class<?>, MessageParamsClass> 
                createMessageParamsClasses(Set<Class<?>> classes) {
        Map<Class<?>, MessageParamsClass> answer = new HashMap<>();
        for(Class<?> clazz : classes)
            answer.put(clazz, new MessageParamsClass(clazz));
        return answer;
    }
    
    protected RequestResponseClass newRequestResponseClass() {
        return new RequestResponseClass();
    }
    
    private RequestResponseClass newAndInitRequestResponseClass(Class<?> clazz) {
        RequestResponseClass answer = newRequestResponseClass();
        answer.init(clazz);
        return answer;
    }
    
    protected void checkExecuteMethod(RequestResponseClass clazz) {
        clazz.checkExecuteMethod(null, null);
    }
    
}
