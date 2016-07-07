package com.tvd12.ezyfox.core.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.command.UserInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class of user agent's class
 * 
 * @author tavandung12
 *
 */

public abstract class ApiUser extends ApiBaseUser {
	
    @Getter @Setter
    protected UserInfo command;
    
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
    private final Map<Class<?>, ApiGameUser> childrenMap
            = new HashMap<>();
    
    /**
     * add game user agent reference to set
     * 
     * @param child
     */
    public final void addChild(ApiGameUser child) {
        children.add(child);
        childrenMap.put(child.getClass(), child);
    }
    
    /**
     * get child by its class
     * 
     * @param clazz the class of agent object
     */
    @SuppressWarnings("unchecked")
    public final <T extends ApiBaseUser> T getChild(Class<?> clazz) {
        Object answer = childrenMap.get(clazz);
        if(answer != null) return (T)answer;
        throw new IllegalStateException("Has no user agent with class " + clazz);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getBuddyProperties()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBuddyProperties> T getBuddyProperties() {
        return (T)buddyProperties;
    }
	
}
