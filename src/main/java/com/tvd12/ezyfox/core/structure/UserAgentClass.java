/**
 * 
 */
package com.tvd12.ezyfox.core.structure;

import lombok.Getter;

/**
 * Support to hold structure of a user agent's class
 * 
 * @see AgentClass
 * 
 * @author tavandung12
 *
 */
public class UserAgentClass extends AgentClass {

    @Getter
    private BuddyClassUnwrapper buddyUnwrapper;
    
    /**
     * @see AgentClass#AgentClass(Class)
     * 
     * @param clazz user agent's class
     */
    public UserAgentClass(Class<?> clazz) {
        super(clazz);
        buddyUnwrapper = new BuddyClassUnwrapper(clazz);
    }

    
    
}
