/**
 * 
 */
package com.tvd12.ezyfox.core.content.impl;

import java.util.List;
import java.util.Set;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RequestListenerCenter;
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
    
 // extension configuration object
    protected ExtensionConfiguration extensionConfig;
    
    // holds all request listeners's structure
    protected RequestListenerCenter requestListenerCenter;
    
    /**
     * @return set of client request commands
     */
    public Set<String> getClientRequestCommands() {
        return requestListenerCenter.getCommands();
    }

    /**
     * Get serializer object mapped to the class
     * 
     * @param clazz the class
     * @return the serializer object
     */
    public abstract ObjectSerializer<?> getObjectSerializer(Class<?> clazz);
    
    /**
     * Get deserializer object mapped to the class
     * 
     * @param clazz the class
     * @return the deserializer object
     */
    public abstract ObjectDeserializer<?> getObjectDeserializer(Class<?> clazz);
    
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
    public List<RequestResponseClass> getClientRequestListeners(String command) {
        return requestListenerCenter.getListeners(command);
    }
    
    /**
     * Get all request listener classes and read their structure
     */
    protected void initRequestListenerCenter() {
        requestListenerCenter = new RequestListenerCenter();
        requestListenerCenter.addListeners(extensionConfig.getRequestResponseClientClasses());
    }
    
}
