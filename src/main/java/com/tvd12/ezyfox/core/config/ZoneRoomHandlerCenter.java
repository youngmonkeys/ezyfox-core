/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.core.structure.ZoneRoomHandlerClass;

/**
 * @author tavandung12
 *
 */
public class ZoneRoomHandlerCenter extends ServerEventHandlerCenter {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.ServerEventHandlerCenter#newHandler(java.lang.Class, java.lang.Class[])
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends ServerHandlerClass> T newHandler(Class<?> clazz, Class<?>... roomClasses) {
        return (T) new ZoneRoomHandlerClass(clazz, roomClasses);
    }
    
}
