package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
	
	// set of types to ignore
	protected Set<Class<?>> ignoredTypes;
	
	 // list of setter method's structure
    protected List<MethodCover> methods;
    
 // list of getter methods's structure refer to {@code clazz}
    protected List<MethodCover> references;
	
	// new instance
    protected ClassCover() {
        this.init();
    }
	
	// construct with java class
    public ClassCover(Class<?> clazz) {
        this();
        init(clazz);
    }
    
    /**
     * Initialize fields
     */
    protected void init() {
        this.methods = new ArrayList<>();
        this.references = new ArrayList<>();
    }
	
	/**
	 * Initialize with java class
	 * 
	 * @param clazz the class to parse
	 */
	protected void init(Class<?> clazz) {
	    this.initComponents(clazz);
		this.initWithFields();
        this.initWithMethods();
	}
	
	protected void initComponents(Class<?> clazz) {
	    this.clazz = clazz;
	    this.ignoredTypes = ignoredTypes();
	    this.defaultMethods = defaultMethods();
	}
	
	/**
     * Parse annotated fields to get getter methods and their structure
     */
    protected void initWithFields() {
        List<Field> fields = getAnnotatedFields();
        for(Field field : fields) {
            if(typeFilter().filter(field.getType()))
                addMethod(initWithField(field));
        }
    }
    
    /**
     * Parse annotated methods to get getter methods and their structure
     */
    protected void initWithMethods() {
        List<Method> methods = getAnnotatedMethods();
        for(Method method : methods) {
            if(methodFilter().filter(method) && 
                    typeFilter().filter(getMethodType(method)))
                addMethod(initWithMethod(method));
        }
    }
    
    /**
     * Parse a java field to get getter method and read it's structure
     * 
     * @param field field to get getter method
     * @return a getter method's structure
     */
    protected abstract MethodCover initWithField(Field field);
    
    /**
     * Parse java method to get it's structure
     * 
     * @param method java method
     * @return a getter method's structure
     */
    protected abstract MethodCover initWithMethod(Method method);
    
    /**
     * Add a method's structure to list
     * 
     * @param method the method to add
     */
    protected abstract void addMethod(MethodCover method);
	
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
	 * @param <T> the type of method structure
	 * @return list of structures
	 */
	public abstract <T extends MethodCover> List<T> getMethods();
	
	/**
	 * Because in a class, sometimes we only care about methods or fields that's annotated with
	 * an annotation, so we need know annotations to filter methods or fields
	 * 
	 * @param <T> the annotation type
	 * @return array of annotations
	 */
	protected abstract <T extends Annotation> Class<T>[] getAnnotationClasses();
	
	/**
	 * Get method associated to key
	 * 
	 * @param <T> the method structure type
	 * @param key the key
	 * @return method the method's structure associated to key
	 */
    @SuppressWarnings("unchecked")
    public <T extends MethodCover> T getMethod(String key) {
        for(MethodCover method : getMethods())
            if(method.getKey().equals(key))
                return (T) method;
        return null;
    }
    
    protected abstract Class<?> getMethodType(Method method);
	
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
    
    protected TypeFilter typeFilter() {
        return new TypeFilter() {
            @Override
            public boolean filter(Class<?> type) {
                return !ignoredTypes.contains(type);
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
	 * We have some types need to ignored
	 * 
	 * @return the set of types need to ignored 
	 */
	private Set<Class<?>> ignoredTypes() {
	    Set<Class<?>> answer = new HashSet<>();
	    answer.add(Class.class);
	    return answer;
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
