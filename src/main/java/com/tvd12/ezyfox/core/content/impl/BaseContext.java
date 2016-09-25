/**
 * 
 */
package com.tvd12.ezyfox.core.content.impl;

import java.util.List;

import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;

/**
 * @author tavandung12
 *
 */
public abstract class BaseContext implements AppContext {

    /**
     * Get serializer object mapped to the class
     * 
     * @param clazz the class
     * @return the serializer object
     */
    public abstract ObjectSerializer getObjectSerializer(Class<?> clazz);
    
    /**
     * Get deserializer object mapped to the class
     * 
     * @param clazz the class
     * @return the deserializer object
     */
    public abstract ObjectDeserializer getObjectDeserializer(Class<?> clazz);
    
    /**
     * Get structure of response parameter class map to the class
     * 
     * @param clazz response parameter class
     * @return structure of response parameter class
     */
    public abstract ResponseParamsClass getResponseParamsClass(Class<?> clazz);
    
    /**
     * Get list of request listeners related to the command
     * 
     * @param command request's command
     * @return list of request listeners
     */
    public abstract List<RequestResponseClass> clientRequestListeners(String command);
    
    
    
}
