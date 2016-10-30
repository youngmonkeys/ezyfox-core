/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

/**
 * @author tavandung12
 *
 */
public interface AppExtensionConfiguration {
    
    /**
     * @return the user class
     */
    Class<?> getUserAgentClass();
    
    /**
     * @return user class structure
     */
    UserAgentClass getUserAgentStruct();
    
    /**
     * @return the set of room classes
     */
    Set<Class<?>> getRoomAgentClasses();

    /**
     * @return the set of game user classes
     */
    Set<Class<?>> getGameUserAgentClasses();

    /**
     * check whether response a message to client automatically or not
     * 
     * @param event event to check
     * @return true or false
     */
    boolean isAutoResponseEvent(String event);
    
    /**
     * @param roomClass the room class
     * @return the room agent class's structure
     */
    AgentClass getRoomAgentClass(Class<?> roomClass);
    
    /**
     * @param clazz the game user class
     * @return the game user class structure
     */
    UserAgentClass getGameUserAgentClass(Class<?> clazz);
    
    /**
     * @return the collection of game user classes structure
     */
    Collection<UserAgentClass> getGameUserAgentStructCollection();
    
    /**
     * @param clazz message parameters class
     * @return the structure of the clazz
     */
    MessageParamsClass getMessageParamsClass(Class<?> clazz);
    
    /**
     * @param clazz response parameters class
     * @return the structure of the clazz
     */
    ResponseParamsClass getResponseParamsClass(Class<?> clazz);
    
    /**
     * @return the map of message parameters classes and their structure
     */
    Map<Class<?>, MessageParamsClass> getMessageParamsStructs();

    /**
     * @return the map of response classes and their structure
     */
    Map<Class<?>, ResponseParamsClass> getResponseParamsStructs();

    /**
     * @param entryPoint the entry point class of the room extension
     * @return the room extension configuration
     */
    RoomExtensionConfiguration getRoomExtensionConfiguration(Class<?> entryPoint);
    
}
