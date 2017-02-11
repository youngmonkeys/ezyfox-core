/**
 * 
 */
package com.tvd12.ezyfox.core.transport;

import java.util.List;
import java.util.Set;

/**
 * @author tavandung12
 *
 */
public interface RoParamers {

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
     * returns the value to which the specified key is mapped, 
     * or null if contains no mapping for the key.
     * 
     * @param <T> type of value
     * @param key key 
     * @return a value 
     */
    <T> T get(Object key);
    
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
    
}
