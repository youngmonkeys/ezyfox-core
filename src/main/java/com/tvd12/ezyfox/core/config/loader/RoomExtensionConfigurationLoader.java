/**
 * 
 */
package com.tvd12.ezyfox.core.config.loader;

import com.tvd12.ezyfox.core.config.RoomExtensionConfigurationImpl;

import lombok.Setter;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationLoader extends ConfigurationLoader {
    
    @Setter
    private Class<?> configClass;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#newExtensionConfiguration()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected RoomExtensionConfigurationImpl newExtensionConfiguration() {
        return new RoomExtensionConfigurationImpl();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#getConfigurationClass()
     */
    @Override
    protected Class<?> getConfigurationClass() {
        return this.configClass;
    }
    
}
