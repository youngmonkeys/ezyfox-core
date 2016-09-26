/**
 * 
 */
package com.tvd12.ezyfox.core.annotation.parser;

import org.springframework.core.annotation.AnnotationUtils;

import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;

/**
 * @author tavandung12
 *
 */
public class ContextConfigParser {

    private ContextConfigParser() {
    }
    
    /** 
     * Get configuration class from application's entry point class 
     *  
     * @param entryPoint the entry point class
     * @return configuration class from application's entry point class 
     *  
     */ 
    public static Class<?> getConfigurationClass(Class<?> entryPoint) {
        ContextConfiguration appConfig = AnnotationUtils
                .findAnnotation(entryPoint, ContextConfiguration.class); 
        if(appConfig == null)
            throw new IllegalStateException(
                    createMessage(ContextConfiguration.class, entryPoint)); 
        return fetchConfigClass(appConfig);
    } 
    
    /**
     * @param annotation the configuration annotation
     * @return the configuration class
     */
    private static Class<?> fetchConfigClass(ContextConfiguration anno) {
        return getConfigClass(anno) != Class.class
                ? getConfigClass(anno)
                : ReflectClassUtil.getClassByName(getConfigClassName(anno));
    }
    
    /**
     * @param annotation the configuration annotation
     * @return the configuration class
     */
    private static Class<?> getConfigClass(ContextConfiguration annotation) {
        return annotation.clazz();
    }
    
    /**
     * @param annotation the configuration annotation
     * @return the configuration class name
     */
    private static String getConfigClassName(ContextConfiguration annotation) {
        return annotation.value();
    }
    
    
    /**
     * Create an exception message
     * 
     * @param annotation annotation
     * @param entryPoint application's entry point class
     * @return message
     */
    private static String createMessage(Class<?> annotation, Class<?> entryPoint) {
        return new StringBuilder()
                .append("You must specific ")
                .append(annotation.getName())
                .append(" in class ")
                .append(entryPoint.getName())
                .toString();
    }
    
    
}
