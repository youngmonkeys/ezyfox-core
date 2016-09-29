/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Map;

import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

/**
 * @author tavandung12
 *
 */
public interface RoomExtensionConfiguration {
    
    /**
     * @return the map of room classes and their structure
     */
    Map<Class<?>, AgentClass> getRoomAgentMap();
    
    /**
     * @return the map of game user classes and their structure
     */
    Map<Class<?>, UserAgentClass> getGameUserAgentMap();
    
}
