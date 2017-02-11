package com.tvd12.ezyfox.core.transport;

import java.util.Map;

/**
 * Support to transport data between objects
 * 
 * @author tavandung12
 *
 */

public interface Parameters extends RoParamers {

    /**
     * remove all parameters
     */
    void clear();
    
    /**
     * Copies all of the mappings from the specified map to this one.
     * 
     * @param values values map
     */
    void setAll(Map<Object, Object> values);
    
    /**
     * Maps the specified key to the specified value in a parameter.
     * 
     * @param key key
     * @param value value
     * @return replaced value
     */
    Object set(Object key, Object value);

    /**
     * Convert the parameters object to map
     * 
     * @return the map object
     */
    Map<Object, Object> toMap();
}
