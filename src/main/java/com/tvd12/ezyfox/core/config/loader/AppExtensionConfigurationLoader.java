package com.tvd12.ezyfox.core.config.loader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.AdditionalAppPackages;
import com.tvd12.ezyfox.core.annotation.AutoResponse;
import com.tvd12.ezyfox.core.annotation.ParentAppConfig;
import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.annotation.RoomPackages;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.annotation.parser.ContextConfigParser;
import com.tvd12.ezyfox.core.config.AdditionalAppExtensionConfiguration;
import com.tvd12.ezyfox.core.config.AppExtensionConfigurationImpl;
import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.reflect.ReflectPackageUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Support to load and hold entire application's configuration
 * 
 * @author tavandung12
 *
 */
public class AppExtensionConfigurationLoader extends ConfigurationLoader {
    
    // the entry point class
    @Getter @Setter 
    private Class<?> entryPoint; 
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.ConfigurationLoader#load(java.lang.Class, java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends ExtensionConfiguration> T load(Class<?> configClass, String[] packages) {
        AppExtensionConfigurationImpl answer = 
                (AppExtensionConfigurationImpl)super.load(configClass, packages);
        answer.setAdditionalConfigurations(createAdditionalExtensionConfigs());
        answer.setRoomExtensionConfigurations(createRoomExtensionConfigs(configClass));
        answer.setAutoResponseEvents(findAutoResponseEvents(configClass));
        answer.setUserAgentClass(findUserClass(packages));
        return (T)answer;
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#newExtensionConfiguration()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected AppExtensionConfigurationImpl newExtensionConfiguration() {
        return new AppExtensionConfigurationImpl();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.loader.BaseConfigurationLoader#getConfigurationClass()
     */
    @Override
    protected Class<?> getConfigurationClass() {
        return ContextConfigParser.getConfigurationClass(getEntryPoint());
    }
    
    protected Map<Class<?>, AdditionalAppExtensionConfiguration>
            createAdditionalExtensionConfigs() {
        Map<Class<?>, AdditionalAppExtensionConfiguration> answer = new HashMap<>();
        if(shouldFindAdditionalExtensionConfigs()) {
            for(Class<?> clazz : findAdditionConfigClasses())
                answer.put(clazz, newAddtionExtensionConfigLoader(clazz).load());
        }
        return answer;
    }
    
    protected boolean shouldFindAdditionalExtensionConfigs() {
        return getConfigurationClass().isAnnotationPresent(AdditionalAppPackages.class);
    }
    
    protected Set<Class<?>> findAdditionConfigClasses() {
        Set<Class<?>> answer = new HashSet<>();
        Set<Class<?>> classes = findAnnotatedClasses(
                getAdditionalPakages(), ParentAppConfig.class);
        for(Class<?> clazz : classes) 
            checkAndAddAdditionConfigClass(answer, clazz);
        return answer;
    }
    
    protected void checkAndAddAdditionConfigClass(
            Set<Class<?>> answer, Class<?> clazz) {
        if(isMyAdditionConfigClass(clazz))
            answer.add(clazz);
    }
    
    protected boolean isMyAdditionConfigClass(Class<?> clazz) {
        return getParentConfigClassFromAdditionConfigClass(clazz)
                .equals(getConfigurationClass().getName());
    }
    
    protected String getParentConfigClassFromAdditionConfigClass(Class<?> clazz) {
        ParentAppConfig anno = clazz.getAnnotation(ParentAppConfig.class);
        return anno.clazz() == Class.class ? anno.value() : anno.clazz().getName();
    }
    
    protected AdditionalAppExtensionConfigurationLoader
            newAddtionExtensionConfigLoader(Class<?> configClass) {
        AdditionalAppExtensionConfigurationLoader answer = 
                new AdditionalAppExtensionConfigurationLoader();
        answer.setConfigurationClass(configClass);
        return answer;
    }
    
    /**
     * Create room extension configurations and put them to map
     * 
     * @param configClass the application's configuration class
     * @return the map of room entry point classes and their configuration
     */
    protected Map<Class<?>, RoomExtensionConfiguration> 
            createRoomExtensionConfigs(Class<?> configClass) {
        Map<Class<?>, RoomExtensionConfiguration> answer = new HashMap<>();
        Set<Class<?>> classes = findRoomConfigurationClasses(configClass);
        for(Class<?> entry : classes) 
            answer.put(entry, loadRoomExtensionConfig(entry));
        return answer;
    }
    
    /**
     * Load room extension configuration
     * 
     * @param configClass the room configuration class
     * @return the room extension configuration
     */
    protected RoomExtensionConfiguration loadRoomExtensionConfig(Class<?> configClass) {
        return newRoomExtensionConfigLoader(configClass).load();
    }
    
    /**
     * Create new room extension configuration loader
     * 
     * @param configClass the room configuration class
     * @return the loader
     */
    protected RoomExtensionConfigurationLoader 
            newRoomExtensionConfigLoader(Class<?> configClass) {
        RoomExtensionConfigurationLoader loader = newRoomExtensionConfigLoader();
        loader.setConfigClass(configClass);
        return loader;
    }
    
    /**
     * @return room extension configuration loader
     */
    protected RoomExtensionConfigurationLoader newRoomExtensionConfigLoader() {
        return new RoomExtensionConfigurationLoader();
    }
    
    /**
     * Find all room extension entry point classes
     * 
     * @param configClass the application's configuration class
     * @return the set of room extension entry point
     */
    protected Set<Class<?>> findRoomConfigurationClasses(Class<?> configClass) {
        return new HashSet<>(ReflectPackageUtil.findClasses(
                getRoomPackages(configClass), RoomContextConfiguration.class));
    }
    
    /**
     * Get array of packages that contains room extension entry point class
     * 
     * @param configClass the application configuration class
     * @return the array of packages
     */
    protected String[] getRoomPackages(Class<?> configClass) {
        return configClass.isAnnotationPresent(RoomPackages.class)
                ? configClass.getAnnotation(RoomPackages.class).packages()
                : new String[] {"com"};
    }
    
    protected String[] getAdditionalPakages() {
        return getConfigurationClass().getAnnotation(AdditionalAppPackages.class).packages();
    }
    
    /**
     * Find set of auto response events
     * 
     * @param configClass the application configuration class
     * @return the set of auto response events
     */
    protected Set<String> findAutoResponseEvents(Class<?> configClass) { 
        Set<String> answer = new HashSet<>();
        AutoResponse annotation = AnnotationUtils
                .findAnnotation(configClass, AutoResponse.class);
        if(annotation != null)
            answer.addAll(Arrays.asList(annotation.events()));
        return answer;
    }
    
    /**
     * Find user class
     * 
     * @param packages the packages to find
     * @return the user class
     */
    protected Class<?> findUserClass(String[] packages) {
        Set<Class<?>> classes = findAnnotatedClasses(packages, UserAgent.class);
        if(classes.size() != 1)
            throw new IllegalStateException(
                    exceptionMsgWhenFindingAgent(UserAgent.class, classes.size()));
        Class<?> answer = classes.iterator().next();
        checkAgentClass(answer, ApiUser.class);
        return answer;
            
    }

}
    
