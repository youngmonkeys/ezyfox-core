/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;

/**
 * @author tavandung12
 *
 */
public class ObjectDeserializerMapper {
    
    // map of class and it's serializer
    protected Map<Class<?>, ObjectDeserializer<?>> deserializers;
    
    /**
     * Init map
     */
    public ObjectDeserializerMapper() {
        deserializers = new ConcurrentHashMap<>();
    }
    
    /**
     * Map the class to it's serializer
     * 
     * @param clazz the class
     * @param serializer the serializer
     */
    public void add(Class<?> clazz, ObjectDeserializer<?> serializer) {
        deserializers.put(clazz, serializer);
    }
    
    /**
     * Remove the class and it's serializer from the map
     * 
     * @param clazz the class
     */
    public void remove(Class<?> clazz) {
        deserializers.remove(clazz);
    }
    
    /**
     * Clear the map
     */
    public void clear() {
        deserializers.clear();
    }
    
    /**
     * Get the deserializer object that mapped to the class
     * 
     * @param clazz the class
     * @return the deserializer object
     */
    public ObjectDeserializer<?> get(Class<?> clazz) {
        ObjectDeserializer<?> answer = deserializers.get(clazz);
        if(answer != null) return answer;
        throw new IllegalArgumentException("Has no deserializer object map to " + clazz);
    }

}
