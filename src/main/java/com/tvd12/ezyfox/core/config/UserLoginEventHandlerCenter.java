/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.core.structure.UserLoginHandlerClass;

/**
 * Support to load and hold all structures of server event handler's classes
 * 
 * @author tavandung12
 *
 */
public class UserLoginEventHandlerCenter extends ServerEventHandlerCenter {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.ServerEventHandlerCenter#newHandler(java.lang.Class, java.lang.Class[])
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends ServerHandlerClass> T newHandler(Class<?> clazz,
            Class<?>... paramTypes) {
        return (T) new UserLoginHandlerClass(clazz, paramTypes);
    }
    
}
