package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.ConfigProperty;

/**
 * Hold structure of method be annotated with {@code ConfigProperty} annotation
 * 
 * @see SetterMethodCover
 * 
 * @author tavandung12
 *
 */

public class PropertySetterMethod extends SetterMethodKeys {
    
    /**
     * @param clazz the parameter type
     * @param field the java field
     */
    public PropertySetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
    }
    
    /**
     * @param clazz the return type
     * @param method the java method
     */
    public PropertySetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.SetterMethodKeys#getAnnotationValue(java.lang.annotation.Annotation)
     */
    @Override
    protected String getAnnotationValue(Annotation annotation) {
        return ((ConfigProperty)annotation).value();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.SetterMethodKeys#getAnnotationClassOfFieldAndMethod()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected <T extends Annotation> Class<T> getAnnotationClassOfFieldAndMethod() {
        return (Class<T>) ConfigProperty.class;
    }
    
}
