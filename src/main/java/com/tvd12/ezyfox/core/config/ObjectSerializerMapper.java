/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfox.core.serialize.ObjectSerializer;

/**
 * @author tavandung12
 *
 */
public class ObjectSerializerMapper {
    
    // map of class and it's serializer
    protected Map<Class<?>, ObjectSerializer<?>> serializers;
    
    /**
     * Init map
     */
    public ObjectSerializerMapper() {
        serializers = new ConcurrentHashMap<>();
    }
    
    /**
     * Map the class to it's serializer
     * 
     * @param clazz the class
     * @param serializer the serializer
     */
    public void add(Class<?> clazz, ObjectSerializer<?> serializer) {
        serializers.put(clazz, serializer);
    }
    
    /**
     * Remove the class and it's serializer from the map
     * 
     * @param clazz the class
     */
    public void remove(Class<?> clazz) {
        serializers.remove(clazz);
    }
    
    /**
     * Clear the map
     */
    public void clear() {
        serializers.clear();
    }
    
    /**
     * Get the serializer object that mapped to the class
     * 
     * @param clazz the class
     * @return the serializer object
     */
    public ObjectSerializer<?> get(Class<?> clazz) {
        ObjectSerializer<?> answer = serializers.get(clazz);
        if(answer != null) return answer;
        throw new IllegalArgumentException("Has no deserializer object map to " + clazz);
    }

}
