package com.tvd12.ezyfox.core.config.loader;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.AdditionalServerEventHandlers;
import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.config.BaseExtensionConfiguration;
import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Support to read configuration class and hold packages to scan
 * 
 * @author tavandung12
 *
 */
@Getter
public abstract class BaseConfigurationLoader {
    
    // the entry point class
    @Setter
    private Class<?> entryPoint; 
    
	/**
	 * Load configuration from entry point class of application
	 * 
	 * @param <T> extension configuration type
	 * @return the extension configuration
	 */
	public <T extends ExtensionConfiguration> T load() {
		return load(getConfigurationClass());
	}
	
	/**
     * Load configuration from entry point class of application
     * 
     * @param <T> extension configuration type
     * @param configClass the configuration class
     * @return the extension configuration
     */
	protected <T extends ExtensionConfiguration> T load(Class<?> configClass) {
	    T answer = load(configClass, getPackagesScan(configClass));
	    ((BaseExtensionConfiguration)answer).checkAll();
	    return answer;
	}
	
	/**
     * Load configuration from entry point class of application
     * 
     * @param <T> extension configuration type
     * @param configClass the configuration class
     * @param packages the packages to scan all components
     * @return the extension configuration
     */
    protected abstract <T extends ExtensionConfiguration> T load(
	        Class<?> configClass, String[] packages);
	
    /**
     * New extension configuration
     * 
     * @param <T> extension configuration type
     * @return the extension configuration
     */
	protected abstract <T extends BaseExtensionConfiguration> T 
	        newExtensionConfiguration();
	
	/**
	 * Fetch additional sever event handler classes by parse 
	 * {@code AdditionalServerEventHandlers} annotation
	 * 
	 * @param configClass the configuration class
	 * @return set of additional server event handler classes
	 */
	protected Set<Class<?>> fetchAdditionalServerEventHandlers(Class<?> configClass) {
	    Set<Class<?>> answer = new HashSet<>();
	    AdditionalServerEventHandlers annotation = AnnotationUtils
	            .findAnnotation(configClass, AdditionalServerEventHandlers.class);
	    if(annotation == null) return answer;
	    answer.addAll(Arrays.asList(annotation.classes()));
	    answer.addAll(fetchClassesByName(annotation.value()));
	    return answer;
	}
	
	/**
     * Fetch additional client request listener classes by parse 
     * {@code AdditionalServerEventHandlers} annotation
     * 
     * @param configClass the configuration class
     * @return set of additional client request listener classes
     */
	protected Set<Class<?>> fetchAdditionalClientRequestListeners(Class<?> configClass) {
	    Set<Class<?>> answer = new HashSet<>();
	    AdditionalClientRequestListeners annotation = AnnotationUtils
	            .findAnnotation(configClass, AdditionalClientRequestListeners.class);
	    if(annotation == null) return answer;
	    answer.addAll(Arrays.asList(annotation.classes()));
	    answer.addAll(fetchClassesByName(annotation.value()));
	    return answer;
	}
	
	/**
	 * Fetch classes by array of names
	 * 
	 * @param classNames the array of class names
	 * @return the list of classes
	 */
	protected List<Class<?>> fetchClassesByName(String[] classNames) {
	    List<Class<?>> answer = new ArrayList<>();
	    for(String name : classNames)
	        answer.add(ReflectClassUtil.getClassByName(name));
	    return answer;
	}
	
	/**
	 * Get packages to scan
	 * 
	 * @param configClass the configuration class
	 * @return the packages to scan
	 */
	protected String[] getPackagesScan(Class<?> configClass) {
		PackagesScan pkgsScan = AnnotationUtils
				.findAnnotation(configClass, PackagesScan.class);
		if(pkgsScan == null) 
			throw new IllegalStateException(
			        createMessage(PackagesScan.class, configClass));
		return pkgsScan.packages();
	}
	
	/**
	 * Get configuration class from application's entry point class
	 * 
	 * @return configuration class from application's entry point class
	 * 
	 */
	protected Class<?> getConfigurationClass() {
		Annotation appConfig = AnnotationUtils
				.findAnnotation(getEntryPoint(), getConfigAnnotation());
		if(appConfig == null)
			throw new IllegalStateException(
			        createMessage(AppContextConfiguration.class, getEntryPoint()));
		return getConfigClass(appConfig);
	}
	
	/**
	 * @param <T> annotation type
	 * @return the configuration annotation
	 */
	protected abstract <T extends Annotation> Class<T> getConfigAnnotation();
	
	/**
	 * @param <T> annotation type
	 * @param annotation the configuration annotation
	 * @return the configuration class
	 */
	protected abstract <T extends Annotation> Class<?> getConfigClass(T annotation);
	
	/**
	 * Create an exception message
	 * 
	 * @param annotation annotation
	 * @param entryPoint application's entry point class
	 * @return message
	 */
	protected String createMessage(Class<?> annotation, Class<?> entryPoint) {
	    return new StringBuilder()
	            .append("You must specific ")
	            .append(annotation.getName())
	            .append(" in class ")
	            .append(entryPoint.getName())
	            .toString();
	}
	
}
