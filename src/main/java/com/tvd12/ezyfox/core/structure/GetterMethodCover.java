package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * Because sometimes we need serialize or deserialize an object, but using java reflection 
 * is too slow, so we need use reflection in first time to load structure of the object
 * And this class support to hold structure of a method in a object's class
 * 
 * @author tavandung12
 *
 */

public class GetterMethodCover extends MethodCover {
    
    // returned type of method
    @Getter @Setter
    private ClassUnwrapper returnClass;
    
    // structure of method's declaring class
    @Getter @Setter
    private ClassUnwrapper declaringClazz;
    
    /**
     * Construct with java method and it's declaring class
     * 
     * @param clazz declaring class of method
     * @param method method to parse
     */
    public GetterMethodCover(Class<?> clazz, Method method) {
        initWithMethod(clazz, method);
    }
    
    /**
     * Construct with java field and it's declaring class
     * 
     * @param clazz declaring class of field
     * @param field field to get getter method to parse
     */
    public GetterMethodCover(Class<?> clazz, Field field) {
        initWithField(clazz, field);
    }
    
    /**
     * @see MethodCover#initWithMethod(Class, Method)
     */
	@Override
	protected void initWithMethod(Class<?> clazz, Method method) {
		super.initWithMethod(clazz, method);
		ReflectMethodUtil.validateGetterMethod(method);
	}
	
	/**
	 * @see MethodCover#initWithField(Class, Field)
	 */
	@Override
	protected void initWithField(Class<?> clazz, Field field) {
		super.initWithField(clazz, field);
		method = getGetterMethodFromField(clazz, field);
	}
	
	/**
	 * @see MethodCover#getTypeFromMethod(Method)
	 */
	@Override
	protected Class<?> getTypeFromMethod(Method method) {
		return method.getReturnType();
	}
	
	/**
	 * @see MethodCover#getGenericTypeFromMethod(Method)
	 */
	@Override
	protected Class<?> getGenericTypeFromMethod(Method method) {
		try {
			return ReflectMethodUtil.getReturnGenericType(method);
		} catch (ExtensionException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Get getter method of field
	 * 
	 * @param clazz declaring class
	 * @param field field
	 * @return getter method
	 */
	protected Method getGetterMethodFromField(Class<?> clazz, Field field) {
		try {
			return ReflectFieldUtil.getGetterMethod(clazz, field);
		} catch (ExtensionException e) {
			throw new IllegalStateException("error related to key: " + getKey() + " : " + getType(), e);
		}
	}
	
	/**
	 * Invoke java method
	 * 
	 * @see com.tvd12.ezyfox.core.reflect.ReflectMethodUtil#invokeMethod(Method, Object, Object...)
	 * 
	 * @param obj the object the underlying method is invoked from
	 * @return returned value
	 */
	public Object invoke(Object obj) {
		try {
			return ReflectMethodUtil.invokeMethod(method, obj);
		} catch (ExtensionException e) {
			throw new IllegalStateException(
			        "Can not invoke getter method " 
			        + getMethodName()
			        + " on class " + obj.getClass(), e);
		}
	}
	
}
