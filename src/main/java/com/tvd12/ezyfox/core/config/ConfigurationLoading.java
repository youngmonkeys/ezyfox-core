package com.tvd12.ezyfox.core.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.AdditionalServerEventHandlers;
import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.annotation.AutoResponse;
import com.tvd12.ezyfox.core.annotation.PackagesScan;

import lombok.Getter;

/**
 * 
 * Support to read configuration class and hold packages to scan
 * 
 * @author tavandung12
 *
 */
@Getter
public class ConfigurationLoading {

    // configuration class
	protected Class<?> configClass;
	
	// packages to scan
	protected String[] packagesScan;
	
	// auto response events
	protected Set<String> autoResponseEvents;
	
	protected Set<Class<?>> additionalServerEventHandlers;
	
	protected Set<Class<?>> additionalClientRequestHandlers;
	
	/**
	 * Load configuration from entry point class of application
	 * 
	 * @param entryPoint application's entry point class
	 */
	public void load(Class<?> entryPoint) {
		getConfigurationClass(entryPoint);
		getPackagesScan();
		fetchAutoResponseEvents();
		fetchAdditionalServerEventHandlers();
		fetchAdditionalClientRequestListeners();
	}
	
	protected void fetchAdditionalServerEventHandlers() {
	    additionalServerEventHandlers = new HashSet<>();
	    AdditionalServerEventHandlers annotation = AnnotationUtils
	            .findAnnotation(configClass, AdditionalServerEventHandlers.class);
	    if(annotation == null) return;
	    additionalServerEventHandlers.addAll(Arrays.asList(annotation.classes()));
	}
	
	protected void fetchAdditionalClientRequestListeners() {
	    additionalClientRequestHandlers = new HashSet<>();
	    AdditionalClientRequestListeners annotation = AnnotationUtils
	            .findAnnotation(configClass, AdditionalClientRequestListeners.class);
	    if(annotation == null) return;
	    additionalClientRequestHandlers.addAll(Arrays.asList(annotation.classes()));
	}
	
	protected void fetchAutoResponseEvents() {
	    autoResponseEvents = new HashSet<>();
	    AutoResponse annotation = AnnotationUtils
	            .findAnnotation(configClass, AutoResponse.class);
	    if(annotation == null) return;
	    autoResponseEvents.addAll(Arrays.asList(annotation.events()));
	}
	
	/**
	 * Get packages to scan
	 */
	protected void getPackagesScan() {
		PackagesScan pkgsScan = AnnotationUtils
				.findAnnotation(configClass, PackagesScan.class);
		if(pkgsScan == null) 
			throw new RuntimeException(
			        createMessage(PackagesScan.class, configClass));
		packagesScan = pkgsScan.packages();
	}
	
	/**
	 * Get configuration class from application's entry point class
	 * 
	 * @param entryPoint application's entry point class
	 */
	protected void getConfigurationClass(Class<?> entryPoint) {
		AppContextConfiguration appConfig = AnnotationUtils
				.findAnnotation(entryPoint, AppContextConfiguration.class);
		if(appConfig == null)
			throw new RuntimeException(
			        createMessage(AppContextConfiguration.class, entryPoint));
		configClass = appConfig.clazz();
	}
	
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
