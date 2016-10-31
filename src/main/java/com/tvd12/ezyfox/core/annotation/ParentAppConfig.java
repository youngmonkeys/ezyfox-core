package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add additional configuration to app context
 * 
 * @author tavandung12
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ParentAppConfig {
    
    /**
     * @return the parent configuration class name
     */
    public String value() default "";
    
    /**
     * @return the parent configuration class
     */
    public Class<?> clazz() default Class.class;
    
}
