/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.structure.RoomRequestResponseClass;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfiguration extends ExtensionConfiguration {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.ConfigurationLoading#getConfigurationClass(java.lang.Class)
     */
    @Override
    protected void getConfigurationClass(Class<?> entryPoint) {
        RoomContextConfiguration appConfig = AnnotationUtils
                .findAnnotation(entryPoint, RoomContextConfiguration.class);
        if(appConfig == null)
            throw new RuntimeException(
                    createMessage(RoomContextConfiguration.class, entryPoint));
        configClass = appConfig.clazz();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.ExtensionConfiguration#newRequestResponseClass(java.lang.Class)
     */
    @Override
    protected RequestResponseClass newRequestResponseClass(Class<?> clazz) {
        return new RoomRequestResponseClass(clazz, getUserClass(), getGameUserClasses());
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.ExtensionConfiguration#initUserAgentClass()
     */
    @Override
    protected void initUserAgentClass() {
    }
}
