package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Indicates that field or method holds value in variable's object value
 * 
 * @author tavandung12
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface VariableParam {

    /**
     * variable parameter's name
     * 
     * @return parameter's name
     */
	public String value() default "";
	
	/**
     * variable parameter's name
     * 
     * @return parameter's name
     */
	public String name() default "";
	
	/**
	 * visible with client or not
	 * 
	 * @return visible with client or not
	 */
	public boolean visible() default true;
	
}
