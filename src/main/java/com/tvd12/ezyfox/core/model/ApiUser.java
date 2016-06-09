package com.tvd12.ezyfox.core.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class of user agent's class
 * 
 * @author tavandung12
 *
 */

public abstract class ApiUser extends ApiBaseUser {
	
    // user id
    @Setter @Getter
    private int id;
    
    // user name
    @Setter @Getter
    private String name;
    
    // user ip
    @Setter @Getter
    private String ip;
    
    @Setter
    private ApiBuddyProperties buddyProperties;
    
    // set of game user agent reference
    @Getter
    private final Set<ApiGameUser> children
            = new HashSet<>(); 
    
    /**
     * add game user agent reference to set
     * 
     * @param child
     */
    public final void addChild(ApiGameUser child) {
        children.add(child);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiBaseUser#getBuddyProperties()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBuddyProperties> T getBuddyProperties() {
        return (T)buddyProperties;
    }
	
}
