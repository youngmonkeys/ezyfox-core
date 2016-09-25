/**
 * 
 */
package com.tvd12.ezyfox.core.config.loader;

import java.lang.annotation.Annotation;
import java.util.Set;

import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfigurationImpl;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationLoader extends ConfigurationLoader {
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.ConfigurationLoader#load(java.lang.Class, java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends ExtensionConfiguration> T load(Class<?> configClass, String[] packages) {
        RoomExtensionConfigurationImpl answer = 
                (RoomExtensionConfigurationImpl)super.load(configClass, packages);
        answer.setGameUserClass(findGameUserClass(packages));
        answer.setRoomClass(findRoomClass(packages));
        return (T)answer;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#getConfigAnnotation()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<RoomContextConfiguration> getConfigAnnotation() {
        return RoomContextConfiguration.class;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#getConfigClass(java.lang.annotation.Annotation)
     */
    @Override
    protected <T extends Annotation> Class<?> getConfigClass(T annotation) {
        return ((RoomContextConfiguration)annotation).clazz();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#newExtensionConfiguration()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected RoomExtensionConfigurationImpl newExtensionConfiguration() {
        return new RoomExtensionConfigurationImpl();
    }
    
    /**
     * Scan the packages and find room class
     * 
     * @param packages the packages to find
     * @return the room class
     * @throws IllegalStateException when has no room classes or has more than one room classes
     */
    protected Class<?> findRoomClass(String[] packages) {
        Set<Class<?>> classes = findRoomClasses(packages);
        if(classes.size() != 1)
            throw new IllegalStateException(exceptionMsgWhenFindingAgent(RoomAgent.class, classes.size()));
        return classes.iterator().next();
    }
    
    /**
     * Scan the packages and find game user class
     * 
     * @param packages the packages to find
     * @return the room class
     * @throws IllegalStateException when has no game user classes or has more than one game user class
     */
    protected Class<?> findGameUserClass(String[] packages) {
        Set<Class<?>> classes = findGameUserClasses(packages);
        if(classes.size() != 1)
            throw new IllegalStateException(exceptionMsgWhenFindingAgent(GameUser.class, classes.size()));
        return classes.iterator().next();
    }
    
}
