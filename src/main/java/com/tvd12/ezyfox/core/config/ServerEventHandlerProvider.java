package com.tvd12.ezyfox.core.config;

import java.util.Map;

import com.tvd12.properties.file.exception.PropertiesFileException;
import com.tvd12.properties.file.reader.Base64FileReader;
import com.tvd12.properties.file.reader.ClassFetcher;

/**
 * Support to read a properties file (contain map of event name and handler's class name) 
 * and provide a handler's class related to a event name
 * 
 * @author tavandung12
 *
 */

public class ServerEventHandlerProvider {

    // default properties file path
	private static final String CONFIG_FILE_PATH
			= "ezyfox/config/handlers.properties";
	
	// prevent new instance
	private ServerEventHandlerProvider() {}
	
	/**
	 * read default properties file and provide a map of event names and handler's classes
	 * 
	 * @param context class to get resource as stream
	 * @return a map
	 */
	public static Map<Object, Class<?>> provide(Class<?> context) {
	    return provide(context, CONFIG_FILE_PATH);
	}
	
	/**
	 * 
	 * read a properties file and provide a map of event names and handler's classes
	 * 
	 * @param context class to get resource as stream
	 * @param configFilePath a properties file path
	 * @return a map
	 */
	public static Map<Object, Class<?>> provide(Class<?> context, String configFilePath) {
		try {
		    return new ClassFetcher.Builder()
	                .context(context)
	                .file(configFilePath)
	                .reader(new Base64FileReader())
	                .build()
	                .fetch();
		} catch (PropertiesFileException e) {
			throw new RuntimeException("Can not get classes from properties file " 
					+ CONFIG_FILE_PATH, e);
		}
	}
	
}
