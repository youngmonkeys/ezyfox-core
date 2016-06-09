package com.tvd12.ezyfox.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Each game may have a user agent, and game's user agent must extend this class
 * 
 * @author tavandung12
 *
 */

public abstract class ApiGameUser extends ApiBaseUser {

    // application's user agent
    @Getter @Setter
    protected ApiUser parent;
    
    /**
     * @see com.tvd12.ezyfox.core.model.ApiBaseUser#getId()
     */
    @Override
    public final int getId() {
        return parent.getId();
    }
    
    /**
     * @see com.tvd12.ezyfox.core.model.ApiBaseUser#getName()
     */
    @Override
    public final String getName() {
        return parent.getName();
    }
    
    /**
     * @see com.tvd12.ezyfox.core.model.ApiBaseUser#getIp()
     */
    @Override
    public final String getIp() {
        return parent.getIp();
    }
    
    /**
     * @see com.tvd12.ezyfox.core.model.ApiBaseUser#getBuddyProperties()
     */
    @Override
    public final <T extends ApiBuddyProperties> T getBuddyProperties() {
        return parent.getBuddyProperties();
    }
    
}
