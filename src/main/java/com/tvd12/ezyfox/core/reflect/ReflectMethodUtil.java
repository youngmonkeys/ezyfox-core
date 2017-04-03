package com.tvd12.ezyfox.core.reflect;

import static com.tvd12.ezyfox.core.reflect.ReflectFieldUtil.getSetterMethod;
import static org.reflections.ReflectionUtils.withModifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tvd12.ezyfox.core.exception.ExtensionException;

/**
 * Utilities for working with {@link Method}s by reflection
 * 
 * @author tavandung12
 *
 */

public final class ReflectMethodUtil {
    
    private static final Logger LOGGER 
            = LoggerFactory.getLogger(ReflectMethodUtil.class);
	
    // prevent new instance
	private ReflectMethodUtil() {}
	
	/**
	 * Get method by name
	 * 
	 * @param name method's name
	 * @param clazz declared class of method
	 * @param parameterTypes parameter types of method
	 * @return a method
	 * @throws ExtensionException when can't get method with given name
	 */
	public static Method getMethod(String name,
			Class<?> clazz,
			Class<?>... parameterTypes) throws ExtensionException {
	    Method method = MethodUtils.getAccessibleMethod(
	            clazz, name, parameterTypes);
	    if(method == null) 
	        throw new ExtensionException("Can not get method " + name
	                + " on class " + clazz);
	    return method;
	}
	
	/**
	 * Get set of public methods in a class
	 * 
	 * @param clazz the class
	 * @return set of public methods
	 */
	@SuppressWarnings("unchecked")
    public static Set<Method> getPublicMethodSet(Class<?> clazz) {
	    return ReflectionUtils.getAllMethods(clazz, withModifier(Modifier.PUBLIC));
	}
	
	/**
	 * Get all methods are annotated with a annotation
	 * 
	 * @see org.apache.commons.lang3.reflect.MethodUtils#getMethodsListWithAnnotation(Class, Class)
	 * 
	 * @param clazz declared class of methods
	 * @param annotation annotation class
	 * @return list of methods
	 */
	public static List<Method> getMethodsWithAnnotation(Class<?> clazz,
			Class<? extends Annotation> annotation) {
		return MethodUtils.getMethodsListWithAnnotation(clazz, annotation);
	}
	
	/**
	 * Get all methods be annotated with one of annotation in list of annotations
	 * 
	 * @param clazz declared class of methods
	 * @param annotations list of annotation classes
	 * @return list of annotated method
	 */
	public static List<Method> getMethodsWithAnnotations(Class<?> clazz,
            Class<? extends Annotation>[] annotations) {
        List<Method> result = new ArrayList<>();
        for(Class<? extends Annotation> ann : annotations) {
            result.addAll(getMethodsWithAnnotation(clazz, ann));
        }
        return result;
    }
	
	/**
	 * Get all methods in a class with name
	 * 
	 * @see org.reflections.ReflectionUtils#getAllMethods(Class, com.google.common.base.Predicate...)
	 * 
	 * @param clazz declared class of methods
	 * @param name methods name
	 * @return list of methods have give name
	 */
	@SuppressWarnings("unchecked")
    public static List<Method> getMethodsWithName(Class<?> clazz, String name) {
	    return new ArrayList<>(ReflectionUtils.getAllMethods(clazz, 
	            ReflectionUtils.withName(name)));
	}
	
	/**
	 * Invoke a method
	 * 
	 * @see java.lang.reflect.Method#invoke(Object, Object...)
	 * 
	 * @param method method to invoke
	 * @param obj the object the underlying method is invoked from
	 * @param args parameters of method
	 * @return returned value
	 * @throws ExtensionException when get any errors
	 */
	public static Object invokeMethod(Method method, Object obj, Object... args) 
			throws ExtensionException {
		try {
			return method.invoke(obj, args);
		} catch (IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			throw new ExtensionException("cannot invoke method " 
			        + method.getName()
			        + " on class " + method.getDeclaringClass() 
			        + ((obj != null) ? (" by object of class " + obj.getClass()) : ""), e);
		}
	}
	
	/**
	 * Invoke handle method of server event handler class
	 * 
	 * @param method handle method to call
	 * @param obj server event handler object
	 * @param args parameters of handle method
	 */
	public static void invokeHandleMethod(Method method, Object obj, Object... args){
	    try {
            invokeMethod(method, obj, args);
        } catch (Exception e) {
            LOGGER.error("Invoke handle method error", e);
            throw new RuntimeException("Can not invoke handle method " 
                    + method.getName()
                    + " on class " + method.getDeclaringClass(), e);
        }
	}
	
	/**
	 * Invoke execute method of client request listener class
	 * 
	 * @param method execute method to call
	 * @param obj client request listener object
	 * @param args parameters of execute method
	 */
	public static void invokeExecuteMethod(Method method, Object obj, Object... args){
        try {
            invokeMethod(method, obj, args);
        } catch (Exception e) {
            throw new RuntimeException("Can not invoke execute method " 
                    + method.getName()
                    + " on class " + method.getDeclaringClass(), e);
        }
    }
	
	/**
	 * Invoke a getter method
	 * 
	 * @see org.apache.commons.lang3.reflect.MethodUtils#invokeExactMethod(Object, String)
	 * 
	 * @param obj the object the underlying method is invoked from
	 * @param methodName getter method name
	 * @return returned value
	 * @throws ExtensionException when get any errors
	 */
	public static Object invokeGetterMethod(Object obj, String methodName) 
			throws ExtensionException {
		try {
			return MethodUtils.invokeExactMethod(obj, methodName);
		} catch (NoSuchMethodException 
				| IllegalAccessException 
				| InvocationTargetException e) {
			throw new ExtensionException("cannot invoke method " 
			        + methodName
			        + " on class " + obj.getClass(), e);
		}
	}
	
	/**
	 * Invoke a getter method of a field
	 * 
	 * @param obj the object the underlying method is invoked from
	 * @param field field to get getter method
	 * @return returned valued
	 * @throws ExtensionException when get any errors
	 */
	public static Object invokeGetterMethod(Object obj, Field field)
			throws ExtensionException {
		try {
		    Method getterMethod = ReflectFieldUtil
	                .getGetterMethod(obj.getClass(), field);
			return getterMethod.invoke(obj);
		} catch (IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			throw new ExtensionException("Cannot call getter method of field " 
			        + field.getName()
			        + " on class " + obj.getClass(), e);
		}
	}
	
	/**
	 * 
	 * Invoke setter method of field
	 * 
	 * @param obj the object the underlying method is invoked from
	 * @param field field to receive data
	 * @param value value to set
	 * @throws ExtensionException when has any errors
	 */
	public static void invokeSetterMethod(Object obj, 
			Field field, 
			Object value) throws ExtensionException {
		Method setterMethod = getSetterMethod(obj.getClass(), field);
		try {
			setterMethod.invoke(obj, value);
		} catch (IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			throw new ExtensionException(
					"cannot call setter method " + setterMethod.getName() +
					" on class " + obj.getClass() +
					" with argument = " + field.getType() + 
					" and value = " + value, e);
		}
	}
	
	/**
	 * 
	 * Invoke setter method by name
	 * 
	 * @param obj the object the underlying method is invoked from
	 * @param methodName name of method
	 * @param value value to set
	 * @throws ExtensionException when has any errors
	 */
	public static void invokeSetterMethod(Object obj,
			String methodName,
			Object value) throws ExtensionException {
		try {
			MethodUtils.invokeExactMethod(obj, 
					methodName, value);
		} catch (NoSuchMethodException 
				| IllegalAccessException 
				| InvocationTargetException e) {
			throw new ExtensionException(
					"cannot call setter method " + 
					methodName +
					" on class " + obj.getClass() +
					" and value = " + value, e);
		}
		
	}
	
	/**
	 * validate setter method, a setter method has only parameter and return void
	 * 
	 * @param method method to validate
	 */
	public static void validateSetterMethod(Method method) {
		if(method.getParameterTypes().length != 1 
		        || method.getReturnType() != Void.TYPE) 
			throw new IllegalStateException("Method " 
			        + method.getName() + " is not setter method"
			        + " on class " + method.getDeclaringClass());
	}
	
	/**
	 * validate getter method, a getter method has no parameters and return non-void
	 * 
	 * @param method method to validate
	 */
	public static void validateGetterMethod(Method method) {
		if(method.getReturnType().equals(Void.TYPE)
		        || method.getParameterTypes().length != 0)
			throw new IllegalStateException("Method " 
					+ method.getName() + " is not getter method"
					+ " on class " + method.getDeclaringClass());
	}
	
	/**
	 * Get generic type of setter method's parameter
	 * 
	 * @param method setter method
	 * @return generic type
	 * @throws ExtensionException when parameter's type is not collection of parameter type is map or the collection didn't specific a generic type
	 */
	public static Class<?> getParameterGenericType(Method method) 
			throws ExtensionException {
		validateSetterMethod(method);
		return getGenericType(method, 
				method.getGenericParameterTypes()[0], 
				"parameter");
	}
	
	/**
	 * Get generic type of getter method's return type
	 * 
	 * @param method getter method
	 * @return generic type
	 * @throws ExtensionException when return's type is not collection of return type is map or the collection didn't specific a generic type
	 */
	public static Class<?> getReturnGenericType(Method method) 
			throws ExtensionException {
		validateGetterMethod(method);
		return getGenericType(method, 
				method.getGenericReturnType(), 
				"return type");
	}
	
	/**
	 * Get generic type of getter method's return type or parameter type
	 * 
	 * @param method method to get type
	 * @param type return type of parameter type
	 * @param location setter or getter
	 * @return generic type
	 * @throws ExtensionException when return/parameter's type is not collection of return/parameter type is map or the collection didn't specific a generic type
	 */
	private static Class<?> getGenericType(Method method, Type type, String location) 
			throws ExtensionException {
		if (!(type instanceof ParameterizedType))
			throw new ExtensionException(location + " of " 
					+ method.getName() 
					+ " on class " + method.getDeclaringClass()
					+ " is not generic type");
		
		Type[] types = ((ParameterizedType)type).getActualTypeArguments();
		
		if(types.length != 1)
			throw new ExtensionException(types.length 
					+ " template parameter(s) is not allowed at "
					+ location + " of "
					+ method.getName()
					+ " on class " + method.getDeclaringClass());
		
		if(types[0] instanceof ParameterizedType) 
		    throw new ExtensionException("Unsupport Collection<Collection> "
                    + "data type for parameter on " + location 
                    + " of " + method.getName()
                    + " on class " + method.getDeclaringClass());

		if (!(types[0] instanceof Class<?>)) 
			throw new ExtensionException("You must specific "
					+ "a type for collection in " + location + " of "
					+ method.getName()
			        + " on class " + method.getDeclaringClass());
		return (Class<?>) types[0];
	}
}
