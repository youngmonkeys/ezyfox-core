package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field or a method will hold request data
 * 
 * @author tavandung12
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })

public @interface RequestParam {

	/**
	 * name of parameter
	 * 
	 * @return name of parameter
	 */
	public String value() default "";

}
