package com.tvd12.ezyfox.core.config;

import java.util.Map;

import com.tvd12.properties.file.exception.PropertiesFileException;
import com.tvd12.properties.file.reader.Base64FileReader;
import com.tvd12.properties.file.reader.ClassFetcher;

/**
 * Support read a properties file that contains map of command name and executive class
 * 
 * @author tavandung12
 *
 */
public class CommandProvider {

    //default properties file path
	private static final String CONFIG_FILE_PATH
			= "ezyfox/config/commands.properties";

	//prevent new instance
	private CommandProvider() {
	}

	/**
	 * Read properties file and provide classes
	 * 
	 * @param context class to get resource as stream
	 * @return map of command name and executive class
	 */
	public static Map<Object, Class<?>> provide(Class<?> context) {
	    return provide(context, CONFIG_FILE_PATH);
	}
	
	/**
	 * Read properties file and provide classes
	 * 
	 * @param context class to get resource as stream
	 * @param configFilePath properties file path
	 * @return map of command name and executive class
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
			throw new RuntimeException("Can not classes from properties file " 
					+ CONFIG_FILE_PATH, e);
		}
	}

}
