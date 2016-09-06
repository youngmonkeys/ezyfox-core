/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;

/**
 * Execute this command to register object deserializer to the application context
 * 
 * @author tavandung12
 *
 */
public interface AddObjectDeserializer {

    /**
     * Register object deserializer to the application context
     * 
     * @param clazz the class
     * @param deserializer the deserializer object
     * @return this pointer for chaining
     */
    AddObjectDeserializer add(Class<?> clazz, ObjectDeserializer deserializer);
    
}
