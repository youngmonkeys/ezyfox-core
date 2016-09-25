/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.RoomRequestResponseClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

import lombok.Getter;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationImpl
        extends BaseExtensionConfiguration
        implements ExtensionConfiguration, RoomExtensionConfiguration {
    
    // the room class
    @Getter
    protected Class<?> roomClass;
    
    // the structure of room class
    @Getter
    protected AgentClass roomAgentClass;
    
    // the game user class
    @Getter
    protected Class<?> gameUserClass;
    
    // the game user class structure
    @Getter
    protected UserAgentClass gameUserAgentClass;
    
    /**
     * Set the room class and parse it's structrue
     * 
     * @param clazz the room class
     */
    public void setRoomClass(Class<?> clazz) {
        this.roomClass = clazz;
        this.roomAgentClass = new AgentClass(clazz);
    }
    
    /**
     * Set game user class and parse it's structure
     * 
     * @param clazz the game user class
     */
    public void setGameUserClass(Class<?> clazz) {
        this.gameUserClass = clazz;
        this.gameUserAgentClass = new UserAgentClass(clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#newRequestResponseClass()
     */
    @Override
    protected RequestResponseClass newRequestResponseClass() {
        return new RoomRequestResponseClass();
    }
    
}
