package com.tvd12.ezyfox.core.config;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.annotation.PackagesScan;

/**
 * 
 * Support to read configuration class and hold packages to scan
 * 
 * @author tavandung12
 *
 */
public class ConfigurationLoading {

    // configuration class
	protected Class<?> configClass;
	
	// packages to scan
	protected String[] packagesScan;
	
	/**
	 * Load configuration from entry point class of application
	 * 
	 * @param entryPoint application's entry point class
	 */
	public void load(Class<?> entryPoint) {
		getConfigurationClass(entryPoint);
		getPackagesScan();
	}
	
	/**
	 * Get packages to scan
	 */
	private void getPackagesScan() {
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
	private void getConfigurationClass(Class<?> entryPoint) {
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
	private String createMessage(Class<?> annotation, Class<?> entryPoint) {
	    return new StringBuilder()
	            .append("You must specific ")
	            .append(annotation.getName())
	            .append(" in class ")
	            .append(entryPoint.getName())
	            .toString();
	}
	
}
