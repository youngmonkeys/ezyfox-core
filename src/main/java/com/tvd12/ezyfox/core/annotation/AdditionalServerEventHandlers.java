/**
 * 
 */
package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to register additional server event handlers to application context
 * 
 * @author tavandung12
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface AdditionalServerEventHandlers {

    /**
     * @return array of additional server event handlers class name
     */
    public String[] value() default {};
    
    /**
     * @return array of additional server event handlers classes
     */
    public Class<?>[] classes() default {};
    
}
