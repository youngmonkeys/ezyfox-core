package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a class like a entry point of application 
 * and it may contain configuration for Application Context
 * 
 * @author tavandung12
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ContextConfiguration {
    
    /**
     * configuration class
     *  
     * @return configuration class
     */
	public Class<?> clazz() default Class.class;
	
	/**
     * configuration class name
     *  
     * @return configuration class name
     */
    public String value() default "";
}
