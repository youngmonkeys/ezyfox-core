/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.structure.MessageParamsClass;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;

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
     * @return the map of message parameters classes and their structure
     */
    Map<Class<?>, MessageParamsClass> getMessageParamsClasses();

    /**
     * @return the map of response classes and their structure
     */
    Map<Class<?>, ResponseParamsClass> getResponseParamsClasses();

    /**
     * @return the map of request classes and their structure
     */
    List<RequestResponseClass> getRequestResponseClientClasses();

    /**
     * @return list of server event handler classes
     */
    Set<Class<?>> getServerEventHandlerClasses();

}
