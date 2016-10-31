/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.RoomRequestResponseClass;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationImpl
        extends AbstractExtensionConfiguration
        implements ComplexExtensionConfiguration, RoomExtensionConfiguration {
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#newRequestResponseClass()
     */
    @Override
    protected RequestResponseClass newRequestResponseClass() {
        return new RoomRequestResponseClass();
    }
    
}
