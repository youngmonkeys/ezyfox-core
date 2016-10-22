package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;

/**
 * Because sometimes we need serialize or deserialize user agent or room agent's classes,
 * but using java reflection is too slow, so we need use reflection in first time to load 
 * structure of agent's classes. And this class support to hold structure of a agent's class for serialize
 * 
 * @author tavandung12
 *
 */

public class AgentClassUnwrapper extends ClassUnwrapper {

    // prevent use default constructor
    protected AgentClassUnwrapper() {
        super();
    }

    /**
     * Construct with agent's class
     * 
     * @param clazz agent's class
     */
    public AgentClassUnwrapper(Class<?> clazz) {
        super(clazz);
    }

    /**
     * Create new class's structure holder
     */
    @Override
    protected ClassUnwrapper newClass() {
        return new AgentClassUnwrapper();
    }

    /**
     * Get getter method of field and parse it's structure
     */
    @Override
    protected GetterMethodCover initWithField(Field field) {
        return new VariableGetterMethod(clazz, field);
    }
    
    /**
     * Parse structure of getter method
     */
    @Override
    protected GetterMethodCover initWithMethod(Method method) {
        return new VariableGetterMethod(clazz, method);
    }

    /**
     * Get array of annotations 
     * 
     * @see ClassCover#getAnnotationClasses() 
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<Annotation>[] getAnnotationClasses() {
        return new Class[] {Variable.class, VariableParam.class};
    }
}
