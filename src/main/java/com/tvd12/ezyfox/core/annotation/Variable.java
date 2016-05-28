package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Indicates that a field or method is a server variable (user variable, room variable)
 * 
 * @author tavandung12
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Variable {
	
    /**
     * name of variable
     * 
     * @return name of variable
     */
	public String value() default "";
	
	/**
	 * name of variable
	 * 
	 * @return name of variabel
	 */
	public String name() default "";
	
	/**
	 * Indicates that variable is visible with client or not
	 * 
	 * @return true or false
	 */
	public boolean visible() default false;
}
