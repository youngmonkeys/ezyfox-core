package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;

/**
 * Support to get user agent object or game user agent object from class
 * 
 * @author tavandung12
 *
 */

public final class UserAgentUtil {

    // prevent new instance
    private UserAgentUtil() {}
    
    /**
     * Get user agent object or game user agent object from class
     * 
     * @param agent user agent object
     * @param gameUserClass game user agent's class
     * @return game user agent object
     */
    public static ApiGameUser getGameUser(ApiUser agent, Class<?> gameUserClass) {
        for(ApiGameUser user : agent.getChildren())
            if(user.getClass() == gameUserClass)
                return user;
        return null;
    }
    
}
