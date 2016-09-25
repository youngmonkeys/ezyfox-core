package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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
 * 
 * Support to load and hold entire application's configuration
 * 
 * @author tavandung12
 *
 */
public class AppExtensionConfigurationImpl
        extends BaseExtensionConfiguration
        implements ExtensionConfiguration, AppExtensionConfiguration {
    
    // the user class
    @Getter
    private Class<?> userClass;
    
    // the list of room classes
    @Getter
    private Set<Class<?>> roomClasses;

    // the list of game user classes
    @Getter
    private Set<Class<?>> gameUserClasses;
    
    // the structure of user class
    @Getter
    private UserAgentClass userAgentClass;
    
    // the list of events response a message to client automatically
    @Setter
    private Set<String> autoResponseEvents;
    
    // the map of room classes and their structure
    private Map<Class<?>, AgentClass> roomAgentClasses;

    // the map of game users and their structure
    private Map<Class<?>, UserAgentClass> gameUserAgentClasses;
    
    // the map of room configuration class and their holder
    private Map<Class<?>, RoomExtensionConfiguration> roomExtensionConfigurations;
    
    /**
     * Initialize all components
     */
    public AppExtensionConfigurationImpl() {
        initialize();
    }
    
    /**
     * Initialize all components
     */
    protected void initialize() {
        this.roomClasses = new HashSet<>();
        this.gameUserClasses = new HashSet<>();
        this.roomAgentClasses = new HashMap<>();
        this.gameUserAgentClasses = new HashMap<>();
    }
    
    /**
     * Set user class and parse it's structure
     * 
     * @param clazz the user class
     */
    public void setUserClass(Class<?> clazz) {
        this.userClass = clazz;
        this.userAgentClass = new UserAgentClass(clazz);
    }
    
    /**
     * Set room classes and parse their structure
     * 
     * @param classes the set of room classes
     */
    public void setRoomClasses(Set<Class<?>> classes) {
        this.roomClasses.addAll(classes);
        this.roomAgentClasses.putAll(createRoomAgentClasses(classes));
    }
    
    /**
     * Set game user classes and parse their structure
     * 
     * @param classes the game user classes
     */
    public void setGameUserClasses(Set<Class<?>> classes) {
        this.gameUserClasses.addAll(classes);
        this.gameUserAgentClasses.putAll(createGameUserAgentClasses(classes));
    }
    
    /**
     * Set room extension configurations
     * 
     * @param map the map of room extension configurations and their entry point class
     */
    public void setRoomExtensionConfigurations(Map<Class<?>, RoomExtensionConfiguration> map) {
        roomExtensionConfigurations = map;
        for(RoomExtensionConfiguration cfg : map.values())
            importFromRoomConfiguration(cfg);
        
    }
    
    /**
     * Import all configuration from room extension configuration
     * 
     * @param cfg the room configuration
     */
    private void importFromRoomConfiguration(RoomExtensionConfiguration cfg) {
        ExtensionConfiguration base = (ExtensionConfiguration)cfg;
        this.roomClasses.add(cfg.getRoomClass());
        this.gameUserClasses.add(cfg.getGameUserClass());
        this.roomAgentClasses.put(cfg.getRoomClass(), cfg.getRoomAgentClass());
        this.gameUserAgentClasses.put(cfg.getGameUserClass(), cfg.getGameUserAgentClass());
        this.objectSerializerClasses.putAll(base.getObjectSerializerClasses());
        this.objectDeserializerClasses.putAll(base.getObjectDeserializerClasses());
        this.responseParamsClasses.putAll(base.getResponseParamsClasses());
        this.messageParamsClasses.putAll(base.getMessageParamsClasses());
        this.serverEventHandlerClasses.addAll(base.getServerEventHandlerClasses());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#isAutoResponseEvent()
     */
    @Override
    public boolean isAutoResponseEvent(String event) {
        return autoResponseEvents.contains(event);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getRoomAgentClass(java.lang.Class)
     */
    @Override
    public AgentClass getRoomAgentClass(Class<?> roomClass) {
        return roomAgentClasses.get(roomClass);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getGameUserAgentClass(java.lang.Class)
     */
    @Override
    public UserAgentClass getGameUserAgentClass(Class<?> clazz) {
        return gameUserAgentClasses.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getGameUserAgentClasses()
     */
    @Override
    public Collection<UserAgentClass> getGameUserAgentClasses() {
        return gameUserAgentClasses.values();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getMessageParamsClass(java.lang.Class)
     */
    @Override
    public MessageParamsClass getMessageParamsClass(Class<?> clazz) {
        return messageParamsClasses.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getResponseParamsClass(java.lang.Class)
     */
    @Override
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
        return responseParamsClasses.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getRoomExtensionConfiguration(java.lang.Class)
     */
    @Override
    public RoomExtensionConfiguration getRoomExtensionConfiguration(Class<?> entryPoint) {
        return roomExtensionConfigurations.get(entryPoint);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#unmodifyAll()
     */
    @Override
    protected void unmodifyAll() {
        this.roomClasses = Collections.unmodifiableSet(roomClasses);
        this.gameUserClasses = Collections.unmodifiableSet(gameUserClasses);
        this.autoResponseEvents = Collections.unmodifiableSet(autoResponseEvents);
        this.roomAgentClasses = Collections.unmodifiableMap(roomAgentClasses);
        this.gameUserAgentClasses = Collections.unmodifiableMap(gameUserAgentClasses);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#checkExecuteMethod(com.tvd12.ezyfox.core.structure.RequestResponseClass)
     */
    @Override
    protected void checkExecuteMethod(RequestResponseClass clazz) {
        clazz.checkExecuteMethod(getUserClass(), new ArrayList<>(getGameUserClasses()));
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
    
