package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.BuddyVariableParam;

/**
 * Because sometimes we need serialize or deserialize user agent or room agent's classes,
 * but using java reflection is too slow, so we need use reflection in first time to load 
 * structure of agent's classes. And this class support to hold structure of a agent's class for deserialize
 * 
 * @author tavandung12
 *
 */

public class BuddyClassWrapper extends ClassWrapper {

    // prevent use default constructor
    protected BuddyClassWrapper() {
        super();
    }

    /**
     * Construct with agent's class
     * 
     * @param clazz agent's class
     */
    public BuddyClassWrapper(Class<?> clazz) {
        super(clazz);
    }

    /**
     * Create new class's structure holder
     */
    @Override
    protected ClassWrapper newClass() {
        return new BuddyClassWrapper();
    }

    /**
     * Get setter method of field and parse it's structure
     */
    @Override
    protected SetterMethodCover initWithField(Field field) {
        return new BuddyVariableSetterMethod(clazz, field);
    }
    
    /**
     * Parse structure of setter method
     */
    @Override
    protected SetterMethodCover initWithMethod(Method method) {
        return new BuddyVariableSetterMethod(clazz, method);
    }

    /**
     * Get array of annotations 
     * 
     * @see ClassCover#getAnnotationClasses() 
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<Annotation>[] getAnnotationClasses() {
        return new Class[] {BuddyVariable.class, BuddyVariableParam.class};
    }
}
