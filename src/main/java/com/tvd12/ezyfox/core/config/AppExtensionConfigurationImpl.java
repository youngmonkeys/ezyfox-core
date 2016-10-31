package com.tvd12.ezyfox.core.config;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
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
        extends AbstractExtensionConfiguration
        implements ComplexExtensionConfiguration, AppExtensionConfiguration {
    
    // the user class
    @Setter @Getter
    protected Class<?> userAgentClass;
    
    // the structure of user class
    @Getter
    protected UserAgentClass userAgentStruct;
    
    // the list of events response a message to client automatically
    @Setter
    protected Set<String> autoResponseEvents;
    
    // the map of response classes and their structure
    @Getter
    protected Map<Class<?>, ResponseParamsClass> responseParamsStructs;

    // the map of message parameter classes and their structure
    @Getter
    protected Map<Class<?>, MessageParamsClass> messageParamsStructs;

    // the map of room classes and their structure
    @Getter
    protected Map<Class<?>, AgentClass> roomAgentStructs;

    // the map of game users and their structure
    @Getter
    protected Map<Class<?>, UserAgentClass> gameUserAgentStructs;
    
    // the map of room configuration class and their holder
    protected Map<Class<?>, RoomExtensionConfiguration> roomExtensionConfigurations;
    
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
     * Set additional configuration to the application configuration
     * 
     * @param map the map of configuration classes and their information
     */
    public void setAdditionalConfigurations(Map<Class<?>, AdditionalAppExtensionConfiguration> map) {
        for(AdditionalAppExtensionConfiguration cfg : map.values())
            importFromAdditionalConfiguration(cfg);
    }
    
    /**
     * Set additional configuration to the application configuration
     * 
     * @param cfg the additional configuration
     */
    private void importFromAdditionalConfiguration(BaseExtensionConfiguration cfg) {
        this.importFromBaseConfiguration(cfg);
        this.setRequestResponseClientClasses(cfg.getRequestResponseClientClasses());
    }
    
    /**
     * Set additional configuration to the application configuration
     * 
     * @param cfg the additional configuration
     */
    private void importFromBaseConfiguration(BaseExtensionConfiguration cfg) {
        this.setRoomAgentClasses(cfg.getRoomAgentClasses());
        this.setGameUserAgentClasses(cfg.getGameUserAgentClasses());
        this.setMessageParamsClasses(cfg.getMessageParamsClasses());
        this.setObjectDeserializerClasses(cfg.getObjectDeserializerClasses());
        this.setObjectSerializerClasses(cfg.getObjectSerializerClasses());
        this.setResponseParamsClasses(cfg.getResponseParamsClasses());
        this.setServerEventHandlerClasses(cfg.getServerEventHandlerClasses());
    }
    
    /**
     * Import all configuration from room extension configuration
     * 
     * @param cfg the room configuration
     */
    private void importFromRoomConfiguration(RoomExtensionConfiguration cfg) {
        importFromBaseConfiguration((BaseExtensionConfiguration)cfg);
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
        return roomAgentStructs.get(roomClass);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getGameUserAgentClass(java.lang.Class)
     */
    @Override
    public UserAgentClass getGameUserAgentClass(Class<?> clazz) {
        return gameUserAgentStructs.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getGameUserAgentClasses()
     */
    @Override
    public Collection<UserAgentClass> getGameUserAgentStructCollection() {
        return getGameUserAgentStructs().values();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getMessageParamsClass(java.lang.Class)
     */
    @Override
    public MessageParamsClass getMessageParamsClass(Class<?> clazz) {
        return messageParamsStructs.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getResponseParamsClass(java.lang.Class)
     */
    @Override
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
        return responseParamsStructs.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getRoomExtensionConfiguration(java.lang.Class)
     */
    @Override
    public RoomExtensionConfiguration getRoomExtensionConfiguration(Class<?> entryPoint) {
        return roomExtensionConfigurations.get(entryPoint);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#buildComponents()
     */
    @Override
    protected void buildComponents() {
        super.buildComponents();
        this.userAgentStruct = new UserAgentClass(userAgentClass);
        this.roomAgentStructs = createRoomAgentClasses();
        this.gameUserAgentStructs = createGameUserAgentClasses();
        this.responseParamsStructs = createResponseParamsClasses();
        this.messageParamsStructs = createMessageParamsClasses();
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#unmodifyAll()
     */
    @Override
    protected void unmodifyAll() {
        super.unmodifyAll();
        this.autoResponseEvents = Collections.unmodifiableSet(autoResponseEvents);
        this.gameUserAgentStructs = Collections.unmodifiableMap(gameUserAgentStructs);
        this.messageParamsStructs = Collections.unmodifiableMap(messageParamsStructs);
        this.responseParamsStructs = Collections.unmodifiableMap(responseParamsStructs);
        this.roomAgentStructs = Collections.unmodifiableMap(roomAgentStructs);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#checkExecuteMethod(com.tvd12.ezyfox.core.structure.RequestResponseClass)
     */
    @Override
    protected void checkExecuteMethod(RequestResponseClass clazz) {
        clazz.checkExecuteMethod(getUserAgentClass(), getGameUserAgentClasses());
    }
    
    protected Map<Class<?>, ResponseParamsClass> createResponseParamsClasses() {
        Map<Class<?>, ResponseParamsClass> answer = new HashMap<>();
        for(Class<?> clazz : responseParamsClasses)
            answer.put(clazz, new ResponseParamsClass(clazz));
        return answer;
    }
    
    protected Map<Class<?>, MessageParamsClass> createMessageParamsClasses() {
        Map<Class<?>, MessageParamsClass> answer = new HashMap<>();
        for(Class<?> clazz : messageParamsClasses)
            answer.put(clazz, new MessageParamsClass(clazz));
        return answer;
    }
    
    /**
     * Parse room agent class and put them to map
     * 
     * @return the map of room classes and their structure
     */
    protected Map<Class<?>, AgentClass> createRoomAgentClasses() {
        Map<Class<?>, AgentClass> answer = new HashMap<>();
        for(Class<?> clazz : roomAgentClasses) 
            answer.put(clazz, new AgentClass(clazz));
        return answer;
    }
    
    /**
     * Parse game user agent class and put them to map
     * 
     * @return the map of game user classes and their structure
     */
    protected Map<Class<?>, UserAgentClass> createGameUserAgentClasses() {
        Map<Class<?>, UserAgentClass> answer = new HashMap<>();
        for(Class<?> clazz : gameUserAgentClasses) 
            answer.put(clazz, new UserAgentClass(clazz));
        return answer;
    }
    
}
    
