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
        ApiUser userAgent = (ApiUser)userAgentClass.getWrapper().newInstance();
        for(AgentClass clazz : gameUserAgentClasses) {
            ApiGameUser gameUser = 
                    (ApiGameUser)clazz.getWrapper().newInstance();
            userAgent.addChild(gameUser);
            gameUser.setParent(userAgent);
        }
        userAgent.setName(name);
        
        return userAgent;
        
    }
    
}
