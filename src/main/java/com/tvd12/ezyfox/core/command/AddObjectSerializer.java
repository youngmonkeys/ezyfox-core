/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.serialize.ObjectSerializer;

/**
 * Execute this command to register object serializer to the application context
 * 
 * @author tavandung12
 *
 */
public interface AddObjectSerializer {

    /**
     * Register object serializer to the application context
     * 
     * @param clazz the class
     * @param serializer the serializer object
     * @return this pointer for chaining
     */
    AddObjectSerializer add(Class<?> clazz, ObjectSerializer serializer);
    
}
