package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.ConfigProperty;

/**
 * Hold structure of all methods be annotated with {@code ConfigProperty} annotation
 * 
 * @see ClassWrapper
 * 
 * @author tavandung12
 *
 */

public class PropertiesClassWrapper extends ClassWrapper {

    // prevent new instance with default constructor
    protected PropertiesClassWrapper() {
        super();
    }
    
    /**
     * @param clazz the class to parse
     */
    public PropertiesClassWrapper(Class<?> clazz) {
        super(clazz);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ClassWrapper#initWithField(java.lang.reflect.Field)
     */
    @Override
    protected SetterMethodCover initWithField(Field field) {
        return new PropertySetterMethod(clazz, field);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ClassWrapper#initWithMethod(java.lang.reflect.Method)
     */
    @Override
    protected SetterMethodCover initWithMethod(Method method) {
        return new PropertySetterMethod(clazz, method);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ClassWrapper#newClass()
     */
    @Override
    protected ClassWrapper newClass() {
        return new PropertiesClassWrapper();
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ClassCover#getAnnotationClasses()
     */
    @Override
    @SuppressWarnings("unchecked")
    protected Class<ConfigProperty>[] getAnnotationClasses() {
        return new Class[] {ConfigProperty.class};
    }

}
