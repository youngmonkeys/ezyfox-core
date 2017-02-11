/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Map;

/**
 * @author tavandung12
 *
 */
public interface ServerEventHandlerProvider {

    /**
     * @return the map of server events and handle classes
     */
    Map<Object, Class<?>> provide();
    
}
