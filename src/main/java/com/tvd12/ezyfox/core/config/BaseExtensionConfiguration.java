/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

/**
 * @author tavandung12
 *
 */
public class BaseExtensionConfiguration {

    // the map of classes and their serializer
    @Getter
    protected Map<Class<?>, Class<?>> objectSerializerClasses;

    // the map of classes and their desializer
    @Getter
    protected Map<Class<?>, Class<?>> objectDeserializerClasses;
    
    // list of server event handler classes
    @Getter
    protected Set<Class<?>> serverEventHandlerClasses;
    
    @Getter
    protected Set<Class<?>> responseParamsClasses;
    
    @Getter
    protected Set<Class<?>> messageParamsClasses;
    
    @Getter
    protected Set<Class<?>> requestResponseClientClasses;
    
    @Getter
    protected Set<Class<?>> roomAgentClasses;
    
    @Getter
    protected Set<Class<?>> gameUserAgentClasses;
    
    public BaseExtensionConfiguration() {
        initialize();
    }
    
    /**
     * Initialize all components
     */
    protected void initialize() {
        this.roomAgentClasses = new HashSet<>();
        this.gameUserAgentClasses = new HashSet<>();
        this.responseParamsClasses = new HashSet<>();
        this.messageParamsClasses = new HashSet<>();
        this.objectSerializerClasses = new HashMap<>();
        this.objectDeserializerClasses = new HashMap<>();
        this.serverEventHandlerClasses = new HashSet<>();
        this.requestResponseClientClasses = new HashSet<>();
    }
    
    /**
     * Add the server event handler classes to the set
     * 
     * @param classes the server event handler classes
     */
    public void setServerEventHandlerClasses(Set<Class<?>> classes) {
        this.serverEventHandlerClasses.addAll(classes);
    }
    
    /**
     * Add all object serializer classes to the map
     * 
     * @param classes the map of serializer classes
     */
    public void setObjectSerializerClasses(Map<Class<?>, Class<?>> classes) {
        this.objectSerializerClasses.putAll(classes);
    }
    
    /**
     * Add all object deserializer classes to the map
     * 
     * @param classes the map of deserializer classes
     */
    public void setObjectDeserializerClasses(Map<Class<?>, Class<?>> classes) {
        this.objectDeserializerClasses.putAll(classes);
    }
    
    /**
     * Set message parameter classes and parse their structure
     * 
     * @param classes the classes
     */
    public void setMessageParamsClasses(Set<Class<?>> classes) {
        this.messageParamsClasses.addAll(classes);
    }
    
    /**
     * Set request listener classes and parse their structure
     * 
     * @param classes the classes
     */
    public void setRequestResponseClientClasses(Set<Class<?>> classes) {
        this.requestResponseClientClasses.addAll(classes);
    }
    
    /**
     * Set response parameter classes and parse their structure
     * 
     * @param classes the response parameter classes
     */
    public void setResponseParamsClasses(Set<Class<?>> classes) {
        this.responseParamsClasses.addAll(classes);
    }
    
    /**
     * Set room classes and parse their structure
     * 
     * @param classes the set of room classes
     */
    public void setRoomAgentClasses(Set<Class<?>> classes) {
        this.roomAgentClasses.addAll(classes);
    }
    
    /**
     * Set game user classes and parse their structure
     * 
     * @param classes the game user classes
     */
    public void setGameUserAgentClasses(Set<Class<?>> classes) {
        this.gameUserAgentClasses.addAll(classes);
    }
    
    public void build() {
        this.buildComponents();
        this.unmodifyAll();
        this.checkAll();
    }
    
    /**
     * Check all components
     */
    protected void checkAll() {
    }
    
    protected void buildComponents() {
    }
    
    /**
     * Unmodify all component
     */
    protected void unmodifyAll() {
        this.objectSerializerClasses = Collections.unmodifiableMap(objectSerializerClasses);
        this.objectDeserializerClasses = Collections.unmodifiableMap(objectDeserializerClasses);
        this.serverEventHandlerClasses = Collections.unmodifiableSet(serverEventHandlerClasses);
        this.responseParamsClasses = Collections.unmodifiableSet(responseParamsClasses);
        this.messageParamsClasses = Collections.unmodifiableSet(messageParamsClasses);
        this.requestResponseClientClasses = Collections.unmodifiableSet(requestResponseClientClasses);
        this.roomAgentClasses = Collections.unmodifiableSet(roomAgentClasses);
        this.gameUserAgentClasses = Collections.unmodifiableSet(gameUserAgentClasses);
    }
    
}
