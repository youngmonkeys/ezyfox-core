package com.tvd12.ezyfox.core.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.tvd12.ezyfox.core.exception.ExtensionException;

/**
 * Utilities for working with {@link Field}s by reflection 
 * 
 * @author tavandung12
 *
 */

public final class ReflectFieldUtil {
	
    // prevent new instance
	private ReflectFieldUtil() {}
	
	/**
	 * Get field by name
	 * 
	 * @see java.lang.Class#getDeclaredField(String)
	 * 
	 * @param name field's name
	 * @param clazz declared class of field
	 * @return a field
	 * @throws ExtensionException if a field with the specified name is not found
	 */
	public static Field getField(String name, Class<?> clazz) throws ExtensionException {
		try {
			return clazz.getDeclaredField(name);
		} catch (NoSuchFieldException | SecurityException e) {
			throw new ExtensionException("Can not get field " + name
			        + " on class " + clazz, e);
		}
	}
	
	/**
	 * Get list of fields with annotation
	 * 
	 * @see org.apache.commons.lang3.reflect.FieldUtils#getFieldsListWithAnnotation(Class, Class)
	 * 
	 * @param clazz declared class
	 * @param annotation annotation class
	 * @return list of fields
	 */
	public static List<Field> getFieldsWithAnnotation(Class<?> clazz,
			Class<? extends Annotation> annotation) {
		return FieldUtils.getFieldsListWithAnnotation(clazz, annotation);
	}
	
	/**
	 * Get list of fields with list of annotations
	 * 
	 * @param clazz declared class
	 * @param annotations list of annotation classes
	 * @return a list (empty if has no fields be annotated with any annotation in the list)
	 */
	public static List<Field> getFieldsWithAnnotations(Class<?> clazz,
            Class<? extends Annotation>[] annotations) {
	    List<Field> result = new ArrayList<>();
	    for(Class<? extends Annotation> ann : annotations) {
	        result.addAll(getFieldsWithAnnotation(clazz, ann));
	    }
        return result;
    }
	
	/**
	 * Get type of field and create an instance to that type
	 * 
	 * @param field field to get type
	 * @return an instance of field's type
	 */
	public static Object newInstance(Field field) {
		try {
			return field.getType().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Can not new an instance of class " 
					+ field.getType() 
					+ " for field " 
					+ field.getName()
					+ " on class " + field.getDeclaringClass(), e);
		}
	}
	
	/**
	 * Get properties descriptor of field
	 * 
	 * @param clazz declared class of field
	 * @param field field to get property descriptor
	 * @return property descriptor object
	 * @throws ExtensionException when an exception happens during Introspection
	 */
	private static PropertyDescriptor getPropertyDescriptor(
	        Class<?> clazz, Field field) throws ExtensionException {
	    String property = field.getName();
	    if(property.startsWith("is"))
	        property = property.substring("is".length());
	    try {
            return new PropertyDescriptor(property, clazz);
        } catch (IntrospectionException e) {
            throw new ExtensionException("Has no getter method for field " 
                    + field.getName()
                    + " on class " + field.getDeclaringClass(), e);
        }
	}
	
	/**
	 * Get getter method of field
	 * 
	 * @param clazz declared class of field
	 * @param field field to get getter method 
	 * @return getter method
	 * @throws ExtensionException when has no getter for the field
	 */
	public static Method getGetterMethod(Class<?> clazz, Field field) 
			throws ExtensionException {
	    return getPropertyDescriptor(clazz, field).getReadMethod();
	}
	
	/**
	 * Get setter method of field
	 * 
	 * @param clazz declared class of field
	 * @param field field to get setter method
	 * @return setter method
	 * @throws ExtensionException when has no setter method for the field
	 */
	public static Method getSetterMethod(Class<?> clazz, Field field) 
			throws ExtensionException {
		return getPropertyDescriptor(clazz, field).getWriteMethod();
	}
	
	/**
	 * Get generic type of field if field's type is collection
	 * 
	 * @param field field to get generic type
	 * @return generic type of the field
	 * @throws ExtensionException when field's type is not collection of field type is map or the collection didn't specific a generic type
	 */
	public static Class<?> getGenericType(Field field) throws ExtensionException {
		Type type = field.getGenericType();
		if (!(type instanceof ParameterizedType))
			throw new ExtensionException("The field " 
					+ field.getName()
					+ " on class " + field.getDeclaringClass()
					+ " is not generic type");
		
		Type[] types = ((ParameterizedType)type).getActualTypeArguments();
		
		if(types.length != 1)
			throw new ExtensionException(types.length 
					+ " template parameter(s) is not allowed at the field "
					+ field.getName()
					+ " on class " + field.getDeclaringClass());
		
		if(types[0] instanceof ParameterizedType) 
		    throw new ExtensionException("Unsupport Collection<Collection> "
                    + "data type for parameter on field " 
		            + field.getName()
		            + " on class " + field.getDeclaringClass());

		if (!(types[0] instanceof Class<?>)) 
			throw new ExtensionException("You must specific "
					+ "a type for collection in field "
					+ field.getName()
					+ " on class " + field.getDeclaringClass());
		return (Class<?>) types[0];
	}
	
}
