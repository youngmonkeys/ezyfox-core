package com.tvd12.ezyfox.core.entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;
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
	
    // user id
    @Setter @Getter
    private int id;
    
    // user name
    @Setter @Getter
    private String name;
    
    // user ip
    @Setter @Getter
    private String ip;
    
    @Getter @Setter
    protected UserInfo command;
    
    @Getter @Setter
    protected ApiSession session;
    
    @Setter
    private ApiBuddyProperties buddyProperties;
    
    private final Map<Class<?>, ApiGameUser> childrenMap
            = new HashMap<>();
    
    /**
     * add game user agent reference to set
     * 
     * @param child the game user
     */
    public final void addChild(ApiGameUser child) {
        childrenMap.put(child.getClass(), child);
    }
    
    /**
     * get child by its class
     * 
     * @param <T> the game user type
     * @param clazz the class of agent object
     * @return the game user
     */
    @SuppressWarnings("unchecked")
    public final <T extends ApiBaseUser> T getChild(Class<?> clazz) {
        Object answer = childrenMap.get(clazz);
        if(answer != null) return (T)answer;
        throw new IllegalStateException("Has no user agent with class " + clazz);
    }
    
    /**
     * get collection of children
     * 
     * @return the collection of children
     */
    public final Collection<ApiGameUser> getChildren() {
        return childrenMap.values();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiBaseUser#getBuddyProperties()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBuddyProperties> T getBuddyProperties() {
        return (T)buddyProperties;
    }
	
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) 
            return false;
        if(obj == this)
            return true;
        if(obj instanceof ApiUser)
            return Objects.equal(getName(), ((ApiUser)obj).getName());
        return false;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
