/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

/**
 * @author tavandung12
 *
 */
public interface RoomExtensionConfiguration {
    
    /**
     * @return the room class
     */
    Class<?> getRoomClass();
    
    /**
     * @return the structure of room class
     */
    AgentClass getRoomAgentClass();
    
    /**
     * @return the game user class
     */
    Class<?> getGameUserClass();
    
    /**
     * @return the game user class structure
     */
    UserAgentClass getGameUserAgentClass();
    
}
