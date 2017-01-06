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
public class ServerEventHandlerProviderImpl implements ServerEventHandlerProvider {

    private Class<?> contextClass;
    private String configFilePath;
    
    protected ServerEventHandlerProviderImpl(Builder builder) {
        this.contextClass = builder.contextClass;
        this.configFilePath = builder.configFilePath;
    }
    
	/**
	 * read default properties file and provide a map of event names and handler's classes
	 * 
	 * @return a map
	 */
	@Override
	public Map<Object, Class<?>> provide() {
	    try {
            return new ClassFetcher.Builder()
                    .context(contextClass)
                    .file(configFilePath)
                    .reader(new Base64FileReader())
                    .build()
                    .fetch();
        } catch (PropertiesFileException e) {
            throw new RuntimeException("Can not get classes from properties file " 
                    + configFilePath, e);
        }
	}
	
	public static Builder builder() {
	    return new Builder();
	}
	
	public static class Builder {
	    private Class<?> contextClass;
	    private String configFilePath;
	    
	    public Builder() {
	        this.contextClass = getClass();
	        this.configFilePath = "ezyfox/config/handlers.properties";
	    }
	    
	    public Builder contextClass(Class<?> contextClass) {
	        this.contextClass = contextClass;
	        return this;
	    }
	    
	    public Builder configFilePath(String configFilePath) {
	        this.configFilePath = configFilePath;
	        return this;
	    }
	    
	    public ServerEventHandlerProvider build() {
	        return new ServerEventHandlerProviderImpl(this);
	    }
	}
	
}
