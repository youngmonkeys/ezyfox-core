/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.Map;
import java.util.Set;

/**
 * @author tavandung12
 *
 */
public interface ExtensionConfiguration {

    /**
     * @return the map of classes and their serializer
     */
    Map<Class<?>, Class<?>> getObjectSerializerClasses();

    /**
     * @return the map of classes and their desializer
     */
    Map<Class<?>, Class<?>> getObjectDeserializerClasses();
    
    /**
     * @return list of server event handler classes
     */
    Set<Class<?>> getServerEventHandlerClasses();
    
}
