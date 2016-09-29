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

import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

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
    
    // the map of room classes and their structure
    @Getter
    protected Map<Class<?>, AgentClass> roomAgentMap;

    // the map of game users and their structure
    @Getter
    protected Map<Class<?>, UserAgentClass> gameUserAgentMap;
    
    public BaseExtensionConfiguration() {
        initialize();
    }
    
    /**
     * Initialize all components
     */
    protected void initialize() {
        this.roomAgentMap = new HashMap<>();
        this.gameUserAgentMap = new HashMap<>();
    }
    
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
     * Set room classes and parse their structure
     * 
     * @param classes the set of room classes
     */
    public void setRoomClasses(Set<Class<?>> classes) {
        this.roomAgentMap.putAll(createRoomAgentClasses(classes));
    }
    
    /**
     * Set game user classes and parse their structure
     * 
     * @param classes the game user classes
     */
    public void setGameUserClasses(Set<Class<?>> classes) {
        this.gameUserAgentMap.putAll(createGameUserAgentClasses(classes));
    }
    
    /**
     * Check all components
     */
    public void checkAll() {
        unmodifyAll();
        checkRequestResponseClasses();
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
        this.roomAgentMap = Collections.unmodifiableMap(roomAgentMap);
        this.gameUserAgentMap = Collections.unmodifiableMap(gameUserAgentMap);
    }
    
    /**
     * Check all request listener classes
     */
    protected void checkRequestResponseClasses() {
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
    
    /**
     * Parse room agent class and put them to map
     * 
     * @param agentClasses the set of room agent classes
     * @return the map of room classes and their structure
     */
    protected Map<Class<?>, AgentClass> 
                createRoomAgentClasses(Set<Class<?>> agentClasses) {
        Map<Class<?>, AgentClass> answer = new HashMap<>();
        for(Class<?> clazz : agentClasses) 
            answer.put(clazz, new AgentClass(clazz));
        return answer;
    }
    
    /**
     * Parse game user agent class and put them to map
     * 
     * @param agentClasses the set of game user agent classes
     * @return the map of game user classes and their structure
     */
    protected Map<Class<?>, UserAgentClass> 
                createGameUserAgentClasses(Set<Class<?>> agentClasses) {
        Map<Class<?>, UserAgentClass> answer = new HashMap<>();
        for(Class<?> clazz : agentClasses) 
            answer.put(clazz, new UserAgentClass(clazz));
        return answer;
    }
    
}
