package com.tvd12.ezyfox.core.content.impl;

import com.tvd12.ezyfox.core.annotation.parser.ContextConfigParser;
import com.tvd12.ezyfox.core.config.ComplexExtensionConfiguration;
import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.structure.ResponseParamsClass;

/**
 * @author tavandung12
 * Created on Aug 16, 2016
 *
 */
public class BaseRoomContext extends BaseContext {

    protected Class<?> configClasss;
    protected BaseAppContext appContext;
    
    public void init(BaseAppContext appContext, Class<?> entryPoint) {
        this.appContext = appContext;
        this.configClasss = getConfigClass(entryPoint);
        this.extensionConfig = getExtensionConfig();
        this.initRequestListenerCenter();
    }
    
    protected ComplexExtensionConfiguration getExtensionConfig() {
        return (ComplexExtensionConfiguration)appContext.getRoomExtensionConfig(configClasss);
    }
    
    protected Class<?> getConfigClass(Class<?> entryPoint) {
        return ContextConfigParser.getConfigurationClass(entryPoint);
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
     * @see com.tvd12.ezyfox.core.content.AppContext#get(java.lang.Object)
     */
    @Override
    public <T> T get(Object key) {
        return appContext.get(key);
    }
    
    @Override
    public <T> T get(Class<T> clazz) {
    	return appContext.get(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.AppContext#get(java.lang.Object, java.lang.Class)
     */
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return appContext.get(key, clazz);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.AppContext#contains(java.lang.Object)
     */
    @Override
    public boolean contains(Object key) {
    	return appContext.contains(key);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getObjectSerializer(java.lang.Class)
     */
    @Override
    public ObjectSerializer<?> getObjectSerializer(Class<?> clazz) {
        return appContext.getObjectSerializer(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getObjectDeserializer(java.lang.Class)
     */
    @Override
    public ObjectDeserializer<?> getObjectDeserializer(Class<?> clazz) {
        return appContext.getObjectDeserializer(clazz);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.content.impl.BaseContext#getResponseParamsClass(java.lang.Class)
     */
    @Override
    public ResponseParamsClass getResponseParamsClass(Class<?> clazz) {
        return appContext.getResponseParamsClass(clazz);
    }

}
