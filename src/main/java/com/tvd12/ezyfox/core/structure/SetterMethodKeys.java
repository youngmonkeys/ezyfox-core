/**
 * 
 */
package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author tavandung12
 *
 */
public abstract class SetterMethodKeys extends SetterMethodCover {

    /**
     * @param clazz the class
     * @param field the field
     */
    public SetterMethodKeys(Class<?> clazz, Field field) {
        super(clazz, field);
    }
    
    /**
     * @param clazz the class
     * @param method the method
     */
    public SetterMethodKeys(Class<?> clazz, Method method) {
        super(clazz, method);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Field)
     */
    @Override
    protected String getKey(Field field) {
        String key = getAnnotationValue(field);
        return (key.length() > 0) ? key : super.getKey(field);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Method)
     */
    @Override
    protected String getKey(Method method) {
        String value = getAnnotationValue(method);
        return value.length() > 0 ? value : super.getKey(method);
    }
    
    /**
     * Get value of annotation that annotated the element
     * 
     * @param element the element
     * @return the annotation value
     */
    protected String getAnnotationValue(AnnotatedElement element) {
        return getAnnotationValue(element.getAnnotation(getAnnotationClassOfFieldAndMethod())); 
    }
    
    /**
     * Get value of annotation that annotated the element
     * 
     * @param annotation the annotation
     * @return the annotation value
     */
    protected abstract String getAnnotationValue(Annotation annotation);
    
    /**
     * @param <T> the annotation type
     * @return annotation that annotated fields and methods
     */
    protected abstract <T extends Annotation> Class<T> getAnnotationClassOfFieldAndMethod();
    
}
