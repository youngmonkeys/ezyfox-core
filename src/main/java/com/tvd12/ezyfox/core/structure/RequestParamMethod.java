package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.RequestParam;

/**
 * Hold structure of method be annotated with {@code RequestParam} annotation 
 * 
 * @see SetterMethodCover
 * 
 * @author tavandung12
 *
 */

public class RequestParamMethod extends SetterMethodCover {
	
    /**
     * @see SetterMethodCover#SetterMethodCover(Class, Field)
     */
	public RequestParamMethod(Class<?> clazz, Field field) {
		super(clazz, field);
	}
	
	/**
	 * @see SetterMethodCover#SetterMethodCover(Class, Method)
	 */
	public RequestParamMethod(Class<?> clazz, Method method) {
		super(clazz, method);
	}

	/**
	 * @see SetterMethodCover#getKey(Method)
	 */
	@Override
	protected String getKey(Method method) {
		String value = method.getAnnotation(RequestParam.class)
				.value().trim();
		return value.length() > 0 ? value : super.getKey(method);
	}

	/**
	 * @see SetterMethodCover#getKey(Field)
	 */
	@Override
	protected String getKey(Field field) {
		String value = field.getAnnotation(RequestParam.class)
				.value().trim();
		return value.length() > 0 ? value : super.getKey(field);
	}

}
