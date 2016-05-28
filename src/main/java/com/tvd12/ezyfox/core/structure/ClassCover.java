package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectClassUtil;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

import lombok.Getter;

/**
 * Because sometimes we need serialize or deserialize an object, but using java reflection 
 * is too slow, so we need use reflection in first time to load structure of the object.
 * And this class support to hold structure of a object's class
 * 
 * @author tavandung12
 *
 */

public abstract class ClassCover {
	
    // class to parse
    @Getter
	protected Class<?> clazz;
	
    // set of default method
	protected Set<Method> defaultMethods; 
	
	/**
	 * Initialize with java class
	 * 
	 * @param clazz
	 */
	protected void init(Class<?> clazz) {
		this.clazz = clazz;
		this.defaultMethods = defaultMethods();
	}
	
	/**
	 * Get annotated fields
	 * 
	 * @return annotated fields
	 */
	protected List<Field> getAnnotatedFields() {
		return ReflectFieldUtil.getFieldsWithAnnotations(
				clazz, getAnnotationClasses());
	}
	
	/**
	 * Get annotated methods
	 * 
	 * @return annotated methods
	 */
	protected List<Method> getAnnotatedMethods() {
		return ReflectMethodUtil.getMethodsWithAnnotations(
				clazz, getAnnotationClasses());
	}
	
	/**
	 * Get number of parsed method
	 * 
	 * @return number of parsed method
	 */
	public abstract int methodCount();
	
	/**
	 * Check whether method has parsed or not
	 * 
	 * @param method method to check
	 * @return contains or not
	 */
	protected abstract boolean containsMethod(Method method);
	
	/**
	 * validate to filter method
	 * 
	 * @param method method to validate
	 * @return true or false
	 */
	protected abstract boolean validateMethod(Method method);
	
	/**
	 * Get list of structures of method
	 * 
	 * @return list of structures
	 */
	public abstract <T extends MethodCover> List<T> getMethods();
	
	/**
	 * Because in a class, sometimes we only care about methods or fields that's annotated with
	 * an annotation, so we need know annotations to filter methods or fields
	 * 
	 * @return array of annotations
	 */
	protected abstract <T extends Annotation> Class<T>[] getAnnotationClasses();
	
	/**
	 * Get method associated to key
	 * 
	 * @param key key
	 * @return method associated to key
	 */
    @SuppressWarnings("unchecked")
    public <T extends MethodCover> T getMethod(String key) {
        for(MethodCover method : getMethods())
            if(method.getKey().equals(key))
                return (T) method;
        return null;
    }
	
    /**
     * Create a method filter to filter invalid method
     * 
     * @return a method filter object
     */
    protected MethodFilter methodFilter() {
        return new MethodFilter() {
            @Override
            public boolean filter(Method method) {
                return !containsMethod(method)
                        && !isDefaultMethod(method)
                        && validateMethod(method);
            }
        };
    }
	
    /**
     * Create an instance of class
     * 
     * @return an instance
     * @throws IllegalStateException when get any errors
     */
	public Object newInstance() {
	    try {
            return ReflectClassUtil.newInstance(clazz);
        } catch (ExtensionException e) {
           throw new IllegalStateException("Can not create instance of class " + clazz, e);
        }
	}
	
	/**
	 * Check whether method is default method (getClass, hashCode, toString, equals)
	 * 
	 * @param method method to check
	 * @return true or false
	 */
	private boolean isDefaultMethod(Method method) {
	    return defaultMethods.contains(method);
	}
	
	/**
	 * Because {@code java.lang.Object} has some methods (getClass, hashCode, toString, equals), 
     * and we don't care about them, so we list them to ignore
	 * 
	 * @return set of defaut methods
	 */
	private Set<Method> defaultMethods() {
	    Set<Method> methods = new HashSet<>();
	    
	    methods.add(getDefaultMethod("getClass"));
	    methods.add(getDefaultMethod("hashCode"));
	    methods.add(getDefaultMethod("toString"));
	    methods.add(getDefaultMethod("equals", Object.class));
	    return methods;
	}
	
	/**
	 * Because {@code java.lang.Object} has some methods (getClass, hashCode, toString, equals), 
	 * and we don't care about them, so we get them to ignore
	 * 
	 * @param name getClass, hashCode, toString or equals
	 * @param paramTypes parameter types
	 * @return a method
	 */
	private Method getDefaultMethod(String name, Class<?>... paramTypes) {
	    try {
            return ReflectMethodUtil.getMethod(name, clazz, paramTypes);
        } catch (ExtensionException e) {
        }
	    return null;
	}

}
