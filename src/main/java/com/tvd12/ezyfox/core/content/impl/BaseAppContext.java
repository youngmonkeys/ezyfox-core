/**
 * 
 */
package com.tvd12.ezyfox.core.content.impl;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.command.AddObjectDeserializer;
import com.tvd12.ezyfox.core.command.AddObjectSerializer;
import com.tvd12.ezyfox.core.command.impl.AddCommandImpl;
import com.tvd12.ezyfox.core.command.impl.AddObjectDeserializerImpl;
import com.tvd12.ezyfox.core.command.impl.AddObjectSerializerImpl;
import com.tvd12.ezyfox.core.config.AppExtensionConfiguration;
import com.tvd12.ezyfox.core.config.CommandProvider;
import com.tvd12.ezyfox.core.config.ObjectDeserializerMapper;
import com.tvd12.ezyfox.core.config.ObjectSerializerMapper;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.config.ServerEventHandlerClasses;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

/**
 * @author tavandung12
 *
 */
public abstract class BaseAppContext extends BaseContext {
    
    // holds all structures of server event handler classes
    protected ServerEventHandlerClasses serverEventHandlerClasses;
    
    // properties object
    protected Map<Object, Object> properties;
    
    // map of interface names and constructor of command implementation classes
    protected Map<Object, Constructor<?>> commands;
    
    // map of interface names and constructor of command implementation classes in a specific application
    protected Map<Object, Constructor<?>> appCommands;
    
    // map of interface names and constructor of command implementation classes
    protected Map<Object, Constructor<?>> fixedCommands;
    
    // map of classes and their serializers
    protected ObjectSerializerMapper objectSerializers;
    
    // map of classes and their deserializers
    protected ObjectDeserializerMapper objectDeserializers;
    
    /**
     * Initialize the context's components
     * 
     * @param entryPoint entry point's class
     */
    public void initialize(Class<?> entryPoint) {
        this.initExtensionConfig(entryPoint);
        this.initEventHandlerClasses();
        this.initRequestListenerCenter();
        
        this.initProperties();
        
        this.initCommands();
        this.initAppCommands();
        
        this.initObjectSerializerMapper();
        this.initObjectDeserializerMapper();
        this.addObjectSerializerMaps();
        this.addObjectDeserializerMaps();
        this.initFixedCommands();
        this.addFixedCommands();
    }
    
    protected void initProperties() {
        properties = new ConcurrentHashMap<>();
    }
    
    /**
     * Initialize object serializer mapper
     */
    protected void initObjectSerializerMapper() {
        objectSerializers = new ObjectSerializerMapper();
    }
    
    /**
     * Initialize object deserializer mapper
     */
    protected void initObjectDeserializerMapper() {
        objectDeserializers = new ObjectDeserializerMapper();
    }
    
    protected void addObjectSerializerMaps() {
        Map<Class<?>, Class<?>> map = extensionConfig.getObjectSerializerClasses();
        for(Class<?> clazz : map.keySet())
            addObjectSerializer(clazz, (ObjectSerializer) newInstance(map.get(clazz)));
    }
    
    protected void addObjectDeserializerMaps() {
        Map<Class<?>, Class<?>> map = extensionConfig.getObjectDeserializerClasses();
        for(Class<?> clazz : map.keySet())
            addObjectDeserializer(clazz, (ObjectDeserializer) newInstance(map.get(clazz)));
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T newInstance(Class<?> clazz) {
        try {
            return (T)ReflectClassUtil.newInstance(clazz);
        } catch (ExtensionException e) {
            throw new IllegalStateException(e);
        }
    }
    
    protected void initAppCommands() {
        appCommands = new ConcurrentHashMap<>();
    }
    
    protected void initFixedCommands() {
        fixedCommands = new ConcurrentHashMap<>();
    }
    
    /**
     * Register some command to the application context
     */
    protected void addFixedCommands() {
        addFixedCommand(AddCommand.class, AddCommandImpl.class);
        addFixedCommand(AddObjectSerializer.class, AddObjectSerializerImpl.class);
        addFixedCommand(AddObjectDeserializer.class, AddObjectDeserializerImpl.class);
    }
    
    protected void addFixedCommand(Class<?> base, Class<?> impl) {
        try {
            addFixedCommand(base, getFixedCommandContructor(impl));
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not get constructor of command class " 
                    + impl);
        }
    }
    
    protected Constructor<?> getFixedCommandContructor(Class<?> impl) 
            throws ExtensionException {
        return ReflectClassUtil.getConstructor(impl, BaseAppContext.class);
    }
    
    private void addFixedCommand(Class<?> base, Constructor<?> constructor) {
        fixedCommands.put(base.getName(), constructor);
    }
    
    /**
     * Indicate this event will be auto response
     * 
     * @param event the event
     * @return true or false
     */
    public boolean isAutoResponse(String event) {
        return appExtensionConfig().isAutoResponseEvent(event);
    }
    
    /**
     * @see AppContext#set(Object, Object)
     */
    @Override
    public void set(Object key, Object value) {
        properties.put(key, value);
    }
    
    /**
     * @see AppContext#get(Object, Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return (T)properties.get(key);
    }
    
    /**
     * Get structure of user agent class
     * 
     * @return structure of user agent class
     */
    public UserAgentClass getUserAgentClass() {
        return appExtensionConfig().getUserAgentClass();
    }
    
    /**
     * Get collection of game user agent classes structure
     * 
     * @return a collection of user classes structure
     */
    public Collection<UserAgentClass> getGameUserAgentClasses() {
        return appExtensionConfig().getGameUserAgentClasses();
    }
    
    /**
     * Get structure of room agent class map to the class
     * 
     * @param clazz room agent class
     * @return structure of room agent class
     */
    public AgentClass getRoomAgentClass(Class<?> clazz) {
        return appExtensionConfig().getRoomAgentClass(clazz);
    }
    
    /**
     * Get structure of user agent class map to the class
     * 
     * @param clazz room agent class
     * @return structure of user agent class
     */
    public UserAgentClass getUserAgentClass(Class<?> clazz) {
        if(clazz.equals(getUserClass()))
            return getUserAgentClass();
        return getGameUserAgentClass(clazz);
        
        
    }
    
    public UserAgentClass getGameUserAgentClass(Class<?> clazz) {
        return appExtensionConfig().getGameUserAgentClass(clazz);
    }
    
    /**
     * @return user agent's class
     */
    public Class<?> getUserClass() {
        return appExtensionConfig().getUserClass();
    }
    
    /**
     * @return list of room agent classes
     */
    public Set<Class<?>> getRoomClasses() {
        return appExtensionConfig().getRoomClasses();
    }
    
    /**
     * @return list of game user agent classes
     */
    public Set<Class<?>> getGameUserClasses() {
        return appExtensionConfig().getGameUserClasses();
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getResponseParamsClass(java.lang.Class)
     */
    @Override
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
         ResponseParamsClass answer = appExtensionConfig().getResponseParamsClass(clazz);
         return (answer == null) ? new ResponseParamsClass(clazz) : answer;
    }
    
    /**
     * Get structure of message parameter class map to the class
     * 
     * @param clazz message parameter class
     * @return structure of message parameter class
     */
    public MessageParamsClass getMessageParamsClass(Class<?> clazz) {
        return appExtensionConfig().getMessageParamsClass(clazz);
    }
    
    /**
     * Get list of server event handler classes related to the event
     * 
     * @param event the event
     * @return list of server event handler classes
     */
    public Set<Class<?>> getServerEventHandlerClasses(String event) {
        return new HashSet<>(serverEventHandlerClasses.getHandlers(event));
    }

    /**
     * Initialize extension configuration
     * 
     * @param entryPoint application's entry point class
     */
    protected void initExtensionConfig(Class<?> entryPoint) {
        AppExtensionConfigurationLoader loader = 
                newExtensionConfigurationLoader();
        loader.setEntryPoint(entryPoint);
        extensionConfig = loader.load();
    }
    
    /**
     * @return the extension configuration loader
     */
    protected AppExtensionConfigurationLoader newExtensionConfigurationLoader() {
        return new AppExtensionConfigurationLoader();
    }
    
    /**
     * Get all event handler classes and read their structure
     */
    protected void initEventHandlerClasses() {
        serverEventHandlerClasses 
            = new ServerEventHandlerClasses();
        serverEventHandlerClasses.addHandlers(
            extensionConfig.getServerEventHandlerClasses());
    }
    
    /**
     * Read configuration file and add all constructors to map
     */
    protected void initCommands() {
        Map<Object, Class<?>> commandsClass 
                = loadCommandClasses();
        commands = new HashMap<>();
        for(Entry<Object, Class<?>> entry : commandsClass.entrySet()) {
            Constructor<?> constructor = getCommandConstructor(entry.getValue());
            commands.put(entry.getKey(), constructor);
        }
    }
    
    private Map<Object, Class<?>> loadCommandClasses() {
        return CommandProvider.provide(getClass());
    }
    
    /**
     * Get constructor of the command implementation class
     * 
     * @param commandClass command implementation class
     * @return a constructor object
     */
    protected abstract Constructor<?> getCommandConstructor(Class<?> commandClass);
    
    /**
     * Get command by interface class
     * 
     * @param <T> the command type
     * @param clazz interface class
     * @return a command instance
     */
    protected abstract <T> T getCommand(Class<T> clazz);
    
    /**
     * Get command
     * 
     * @param <T> the command type
     * @param map map of command classes and their constructors
     * @param clazz the interface class
     * @param params array of params for constructor
     * @return a command instance
     */
    @SuppressWarnings("unchecked")
    protected <T> T getCommand(Map<Object, Constructor<?>> map, Class<T> clazz, Object... params) {
        Constructor<?> constructor = map.get(clazz.getName());
        if(constructor == null)
            throw new RuntimeException("Can not retrive implementation of command " + clazz);
        try {
            return (T)ReflectClassUtil.newInstance(constructor, params);
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not retrive implementation of command " + clazz, e);
        }
    }
    
    /**
     * Get fixed command
     * 
     * @param <T> the command type
     * @param clazz the interface class
     * @return a command instance
     */
    protected <T> T getFixedCommand(Class<T> clazz) {
        return getCommand(fixedCommands, clazz, this);
    }
    
    /**
     * Get application command by base class
     * 
     * @param <T> the command type
     * @param clazz the base class
     * @return a command instance
     */
    protected <T> T getAppCommand(Class<T> clazz) {
        return getCommand(appCommands, clazz);
    }
    
    /**
     * Add the application command to the map
     * 
     * @param baseClass the interface or abstract class
     * @param implementation the implementation class of command
     */
    public void addAppCommand(Class<?> baseClass, Class<?> implementation) {
        try {
            Constructor<?> constructor = ReflectClassUtil.getConstructor(implementation);
            appCommands.put(baseClass.getName(), constructor);
        } catch (ExtensionException e) {
            throw new RuntimeException("Can not get constructor of command class " 
                    + implementation);
        }
    }
    
    /**
     * Get command by interface class
     * 
     * @return the command object
     */
    public <T> T command(Class<T> clazz) {
        try {
            return getCommand(clazz);
        }
        catch(Exception e1) {
            try {
                return getAppCommand(clazz);
            }
            catch(Exception e2) {
                return getFixedCommand(clazz);
            }
        }
    }
    
    /**
     * Add class and it's serializer object to the map
     * 
     * @param clazz the class
     * @param serializer the serializer object
     */
    public void addObjectSerializer(Class<?> clazz, ObjectSerializer serializer) {
        objectSerializers.add(clazz, serializer);
    }
    
    /**
     * Add class and it's deserializer object to the map
     * 
     * @param clazz the class
     * @param deserializer the deserializer object
     */
    public void addObjectDeserializer(Class<?> clazz, ObjectDeserializer deserializer) {
        objectDeserializers.add(clazz, deserializer);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getObjectSerializer(java.lang.Class)
     */
    @Override
    public ObjectSerializer getObjectSerializer(Class<?> clazz) {
        return objectSerializers.get(clazz);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getObjectDeserializer(java.lang.Class)
     */
    @Override
    public ObjectDeserializer getObjectDeserializer(Class<?> clazz) {
        return objectDeserializers.get(clazz);
    }
    
    /**
     * Get room extension configuration
     * 
     * @param entryPoint the room extension entry point class
     * @return the room extension configuration
     */
    public RoomExtensionConfiguration getRoomExtensionConfig(Class<?> entryPoint) {
        return appExtensionConfig().getRoomExtensionConfiguration(entryPoint);
    }
    
    /**
     * @return the application extension configuration
     */
    private AppExtensionConfiguration appExtensionConfig() {
        return (AppExtensionConfiguration) extensionConfig;
    }
}
