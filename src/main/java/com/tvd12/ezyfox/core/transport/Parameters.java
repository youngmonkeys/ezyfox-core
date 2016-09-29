package com.tvd12.ezyfox.core.transport;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Support to transport data between objects
 * 
 * @author tavandung12
 *
 */

public interface Parameters {

    /**
     * 
     * @return number of parameters
     */
    int size();
    
    /**
     * check if contains no parameter.
     * 
     * @return true or false
     */
    boolean isEmpty();
    
    /**
     * check if the specified object is a key in list of parameters
     * 
     * @param key key
     * @return true or false
     */
    boolean contain(Object key);
    
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
     * returns the value to which the specified key is mapped, 
     * or null if contains no mapping for the key.
     * 
     * @param key key 
     * @return a value 
     */
    Object get(Object key);
    
    /**
     * 
     * returns the value to which the specified key is mapped, 
     * or null if contains no mapping for the key and cast 
     * the value to specific type.
     * 
     * @param <T> the type
     * @param key key
     * @param clazz type of value
     * @return a value
     */
    <T> T get(Object key, Class<T> clazz);
    
    /**
     * @return set of keys
     */
    Set<Object> keys();
    
    /**
     * @return list of values
     */
    List<Object> values();
    
    /**
     * Convert the parameters object to map
     * 
     * @return the map object
     */
    Map<Object, Object> toMap();
    
    
}
