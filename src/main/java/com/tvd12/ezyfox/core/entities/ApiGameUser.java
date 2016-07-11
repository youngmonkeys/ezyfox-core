package com.tvd12.ezyfox.core.entities;

import com.tvd12.ezyfox.core.command.UserInfo;

import lombok.Setter;

/**
 * Each game may have a user agent, and game's user agent must extend this class
 * 
 * @author tavandung12
 *
 */

public abstract class ApiGameUser extends ApiBaseUser {

    // application's user agent
    @Setter
    protected ApiUser parent;
    
    /**
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getId()
     */
    @Override
    public int getId() {
        return parent.getId();
    }
    
    /**
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getName()
     */
    @Override
    public String getName() {
        return parent.getName();
    }
    
    /**
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getIp()
     */
    @Override
    public String getIp() {
        return parent.getIp();
    }
    
    /**
     * Get user agent parent
     * 
     * @return the user agent parent
     */
    @SuppressWarnings("unchecked")
    public <T extends ApiUser> T getParent() {
        return (T)parent;
    }
    
    /**
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getBuddyProperties()
     */
    @Override
    public <T extends ApiBuddyProperties> T getBuddyProperties() {
        return parent.getBuddyProperties();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getCommand()
     */
    @Override
    public final UserInfo getCommand() {
        return parent.getCommand();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getSession()
     */
    @Override
    public final ApiSession getSession() {
        return parent.getSession();
    }
}
