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

public class SetterMethodCover extends MethodCover {
    
    // parameter type of method
    @Getter @Setter
    private ClassWrapper parameterClass;
    
    // structure of method's declaring class
    @Getter @Setter
    private ClassWrapper declaringClazz;
    
    /**
     * Construct with java method and it's declaring class
     * 
     * @param clazz declaring class of method
     * @param method method to parse
     */
    public SetterMethodCover(Class<?> clazz, Method method) {
        initWithMethod(clazz, method);
    }
    
    /**
     * Construct with java field and it's declaring class
     * 
     * @param clazz declaring class of field
     * @param field field to get setter method to parse
     */
    public SetterMethodCover(Class<?> clazz, Field field) {
        initWithField(clazz, field);
    }
	
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#initWithMethod(java.lang.Class, java.lang.reflect.Method)
     */
	@Override
	protected void initWithMethod(Class<?> clazz, Method method) {
		ReflectMethodUtil.validateSetterMethod(method);
		super.initWithMethod(clazz, method);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.structure.MethodCover#initWithField(java.lang.Class, java.lang.reflect.Field)
	 */
	@Override
	protected void initWithField(Class<?> clazz, Field field) {
		super.initWithField(clazz, field);
		method = getSetterMethodFromField(clazz, field);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.structure.MethodCover#getTypeFromMethod(java.lang.reflect.Method)
	 */
	@Override
	protected Class<?> getTypeFromMethod(Method method) {
		return method.getParameterTypes()[0];
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.structure.MethodCover#getGenericTypeFromMethod(java.lang.reflect.Method)
	 */
	@Override
	protected Class<?> getGenericTypeFromMethod(Method method) {
		try {
			return ReflectMethodUtil.getParameterGenericType(method);
		} catch (ExtensionException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
     * Get setter method of field
     * 
     * @param clazz declaring class
     * @param field field
     * @return getter method
     */
	protected Method getSetterMethodFromField(Class<?> clazz, Field field) {
		try {
			return ReflectFieldUtil.getSetterMethod(clazz, field);
		} catch (ExtensionException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
     * Invoke java method
     * 
     * @see com.tvd12.ezyfox.core.reflect.ReflectMethodUtil#invokeMethod(Method, Object, Object...)
     * 
     * @param obj the object the underlying method is invoked from
     * @param value the value to set
     */
	public void invoke(Object obj, Object value) {
		try {
            ReflectMethodUtil.invokeMethod(method, obj, value);
        } catch (ExtensionException e) {
            throw new IllegalStateException("Can not call setter method " 
                    + getMethodName()
                    + " on class " + obj.getClass(), e);
        }
	}
	
}
