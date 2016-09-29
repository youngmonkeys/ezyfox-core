package com.tvd12.ezyfox.core.config;

import java.util.Collection;
import java.util.Collections;
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
    
    // the map of room configuration class and their holder
    private Map<Class<?>, RoomExtensionConfiguration> roomExtensionConfigurations;
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#initialize()
     */
    @Override
    protected void initialize() {
        super.initialize();
        this.roomClasses = new HashSet<>();
        this.gameUserClasses = new HashSet<>();
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
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#setRoomClasses(java.util.Set)
     */
    @Override
    public void setRoomClasses(Set<Class<?>> classes) {
        super.setRoomClasses(classes);
        this.roomClasses.addAll(classes);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#setGameUserClasses(java.util.Set)
     */
    @Override
    public void setGameUserClasses(Set<Class<?>> classes) {
        super.setGameUserClasses(classes);
        this.gameUserClasses.addAll(classes);
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
        importRoomFromRoomConfiguration(cfg);
        imporGameUserFromRoomConfiguration(cfg);
        importFromExtensionConfiguration(base);
    }
    
    private void importFromExtensionConfiguration(ExtensionConfiguration base) {
        this.objectSerializerClasses.putAll(base.getObjectSerializerClasses());
        this.objectDeserializerClasses.putAll(base.getObjectDeserializerClasses());
        this.responseParamsClasses.putAll(base.getResponseParamsClasses());
        this.messageParamsClasses.putAll(base.getMessageParamsClasses());
        this.serverEventHandlerClasses.addAll(base.getServerEventHandlerClasses());
    }
    
    private void importRoomFromRoomConfiguration(RoomExtensionConfiguration cfg) {
        this.roomClasses.addAll(cfg.getRoomAgentMap().keySet());
        this.roomAgentMap.putAll(cfg.getRoomAgentMap());
    }
    
    private void imporGameUserFromRoomConfiguration(RoomExtensionConfiguration cfg) {
        this.gameUserClasses.addAll(cfg.getGameUserAgentMap().keySet());
        this.gameUserAgentMap.putAll(cfg.getGameUserAgentMap());
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
        return roomAgentMap.get(roomClass);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getGameUserAgentClass(java.lang.Class)
     */
    @Override
    public UserAgentClass getGameUserAgentClass(Class<?> clazz) {
        return gameUserAgentMap.get(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.AppExtensionConfiguration#getGameUserAgentClasses()
     */
    @Override
    public Collection<UserAgentClass> getGameUserAgentClasses() {
        return gameUserAgentMap.values();
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
        super.unmodifyAll();
        this.roomClasses = Collections.unmodifiableSet(roomClasses);
        this.gameUserClasses = Collections.unmodifiableSet(gameUserClasses);
        this.autoResponseEvents = Collections.unmodifiableSet(autoResponseEvents);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#checkExecuteMethod(com.tvd12.ezyfox.core.structure.RequestResponseClass)
     */
    @Override
    protected void checkExecuteMethod(RequestResponseClass clazz) {
        clazz.checkExecuteMethod(getUserClass(), getGameUserClasses());
    }
    
}
    
