package com.tvd12.ezyfox.core.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.tvd12.ezyfox.core.exception.ExtensionException;

/**
 * Utilities for working with {@link Class}s by reflection
 * 
 * @author tavandung12
 *
 */

public final class ReflectClassUtil {

    // prevent new instance
	private ReflectClassUtil() {}
	
	/**
	 * Create an instance of class with default constructor
	 * 
	 * @param <T> the type
	 * @param clazz class to create new instance
	 * @return an instance
	 * @throws ExtensionException when can't create class's instance
	 */
	public static <T> T newInstance(Class<T> clazz) throws ExtensionException {
		try {
			return clazz.newInstance();
		} catch (InstantiationException 
				| IllegalAccessException e) {
			throw new ExtensionException("Can not create instance of class " 
					+ clazz, e);
		}
	}
	
	/**
	 * Create an instance of class with only argument constructor
	 * 
	 * @param <T> the type
	 * @param clazz class to create new instance
	 * @param paramType parameter type of constructor
	 * @param value value of parameter
	 * @return an instance
	 * @throws ExtensionException when can't create class's instance
	 */
	public static <T> T newInstance(Class<T> clazz, 
				Class<?> paramType, 
				Object value) 
			throws ExtensionException {
		return newInstance(clazz, 
				new Class<?>[] {paramType}, 
				new Object[] {value});
	}
	
	/**
	 * Create an instance of class
	 * 
	 * @param <T> the type
	 * @param clazz class to create new instance
	 * @param paramTypes array of parameter types
	 * @param values values array of parameters
	 * @return an instance
	 * @throws ExtensionException when can't create class's instance
	 */
	public static <T> T newInstance(Class<T> clazz, 
			Class<?>[] paramTypes, 
			Object[] values) 
			        throws ExtensionException {
    	try {
    		return clazz.getDeclaredConstructor(paramTypes)
    				.newInstance(values);
    	} catch (InstantiationException 
			| IllegalAccessException 
			| IllegalArgumentException 
			| InvocationTargetException 
			| NoSuchMethodException 
            | SecurityException e) {
    	    throw new ExtensionException("Can not create instance of class " 
                    + clazz, e);
    	}
	}
	
	/**
	 * Create an instance of class with constructor and values array of parameter
	 * 
	 * @param <T> the type
	 * @param constructor constructor object
	 * @param values values array of parameters of constructor
	 * @return an instance
	 * @throws ExtensionException when can't create new instance
	 */
	public static <T> T newInstance(Constructor<T> constructor, 
            Object... values) throws ExtensionException {
        try {
            return constructor.newInstance(values);
        } catch (InstantiationException 
            | IllegalAccessException 
            | IllegalArgumentException 
            | InvocationTargetException 
            | SecurityException e) {
            throw new ExtensionException("Can not create instance of class " 
                    + constructor.getDeclaringClass(), e);
        }
    }
	
	/**
	 * Get constructor of specific class
	 * 
	 * @param <T> the type
	 * @param clazz class to get constructor
	 * @param paramTypes parameter types
	 * @return a constructor object
	 * @throws ExtensionException when can't get constructor of class
	 */
	public static <T> Constructor<T> getConstructor(Class<T> clazz, Class<?>... paramTypes) 
	        throws ExtensionException {
	    try {
            return clazz.getDeclaredConstructor(paramTypes);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new ExtensionException("Class " + clazz + " " 
                    + "has no public constructors with "
                    + "parameter types = " + StringUtils.join(paramTypes, ", "));
        }
	}
	
	/**
	 * Get default constructor of specific class
	 * 
	 * @param <T> the type
	 * @param clazz class to get constructor
	 * @return default constructor object
	 * @throws ExtensionException when can't get constructor
	 */
	public static <T> Constructor<T> getDefaultConstructor(Class<T> clazz) 
	        throws ExtensionException {
	    return getConstructor(clazz);
	}
	
	/**
	 * Get class by name
	 * 
	 * @param className class's name
	 * @return the class
	 */
	public static Class<?> getClassByName(String className) {
	    try {
            return ClassUtils.getClass(className);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
	}
}
