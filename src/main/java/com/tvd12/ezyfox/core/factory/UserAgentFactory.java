package com.tvd12.ezyfox.core.factory;

import java.util.Collection;

import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.structure.AgentClass;
import com.tvd12.ezyfox.core.structure.UserAgentClass;

/**
 * Support to create user agent from user agent's class and game user agent's classes
 * 
 * @author tavandung12
 *
 */
public final class UserAgentFactory {

    //prevent new instance
    private UserAgentFactory() {}
    
    /**
     * Create new user agent instance 
     * 
     * @param name name of user
     * @param userAgentClass user agent's class
     * @param gameUserAgentClasses game user agent's classes
     * @return an user agent object
     */
    public static ApiUser newUserAgent(String name,
            AgentClass userAgentClass, 
            Collection<UserAgentClass> gameUserAgentClasses) {
        ApiUser userAgent = newUserAgent(userAgentClass, name);
        for(AgentClass clazz : gameUserAgentClasses) {
            ApiGameUser gameUser = newGameUserAgent(clazz);
            gameUser.setParent(userAgent);
            userAgent.addChild(gameUser);
        }
        
        return userAgent;
        
    }
    
    /**
     * New and set name to game user agent
     * 
     * @param agentClass the structure of user agent class
     * @return the user agent
     */
    private static ApiGameUser newGameUserAgent(AgentClass agentClass) {
        return (ApiGameUser)agentClass.getWrapper().newInstance();
    }
    
    /**
     * New and set name to user agent
     * 
     * @param agentClass the structure of user agent class
     * @param name the user name
     * @return the user agent
     */
    private static ApiUser newUserAgent(AgentClass agentClass, String name) {
        ApiUser userAgent = (ApiUser)agentClass.getWrapper().newInstance();
        userAgent.setName(name);
        return userAgent;
    }
    
}
