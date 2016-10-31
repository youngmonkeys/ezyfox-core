/**
 * 
 */
package com.tvd12.ezyfox.core.config.loader;

import com.tvd12.ezyfox.core.config.AdditionalAppExtensionConfiguration;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 *
 */
public class AdditionalAppExtensionConfigurationLoader extends ConfigurationLoader {

    @Getter @Setter
    protected Class<?> configurationClass;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#load()
     */
    @SuppressWarnings("unchecked")
    @Override
    public AdditionalAppExtensionConfiguration load() {
        return super.load();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#newExtensionConfiguration()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AdditionalAppExtensionConfiguration newExtensionConfiguration() {
        return new AdditionalAppExtensionConfiguration();
    }

    
}
