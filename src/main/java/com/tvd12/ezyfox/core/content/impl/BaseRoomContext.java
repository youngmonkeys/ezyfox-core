package com.tvd12.ezyfox.core.content.impl;

import java.util.List;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class BaseRoomContext extends BaseContext {
    
    protected BaseAppContext appContext;
    private RoomExtensionConfiguration config;
    
    public void init(Class<?> entryPoint, BaseAppContext appContext) {
        this.appContext = appContext;
        this.config = appContext.getRoomExtensionConfig(entryPoint);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.AppContext#command(java.lang.Class)
     */
    @Override
    public <T> T command(Class<T> clazz) {
        return appContext.command(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.AppContext#set(java.lang.Object, java.lang.Object)
     */
    @Override
    public void set(Object key, Object value) {
        appContext.set(key, value);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.AppContext#get(java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return appContext.get(key, clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getObjectSerializer(java.lang.Class)
     */
    @Override
    public ObjectSerializer getObjectSerializer(Class<?> clazz) {
        return appContext.getObjectSerializer(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getObjectDeserializer(java.lang.Class)
     */
    @Override
    public ObjectDeserializer getObjectDeserializer(Class<?> clazz) {
        return appContext.getObjectDeserializer(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getResponseParamsClass(java.lang.Class)
     */
    @Override
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
        return appContext.getResponseParamsClass(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#clientRequestListeners(java.lang.String)
     */
    @Override
    public List<RequestResponseClass> clientRequestListeners(String command) {
        return ((ExtensionConfiguration)config).getRequestResponseClientClasses();
    }
    
    
}
