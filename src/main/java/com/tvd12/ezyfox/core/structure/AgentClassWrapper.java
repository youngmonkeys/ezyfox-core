package com.tvd12.ezyfox.core.structure;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;

/**
 * Because sometimes we need serialize or deserialize an object, but using java reflection 
 * is too slow, so we need use reflection in first time to load struct of the object.
 * And this class support to hold structure of a object's class, it's hold structure of
 * all setter methods
 * 
 * @author tavandung12
 *
 */

public class AgentClassWrapper extends ClassWrapper {

    // prevent new instance
    protected AgentClassWrapper() {
    }

    /**
     * Construct with java class
     * 
     * @param clazz
     */
    public AgentClassWrapper(Class<?> clazz) {
        super(clazz);
    }

    /**
     * Create a structure holder object
     */
    @Override
    protected ClassWrapper newClass() {
        return new AgentClassWrapper();
    }

    /**
     * Get setter method of field and parse that method
     * 
     * @param field field to get setter method
     */
    @Override
    protected SetterMethodCover initWithField(Field field) {
        return new VariableSetterMethod(clazz, field);
    }
    
    /**
     * Parse method to get structure
     */
    @Override
    protected SetterMethodCover initWithMethod(Method method) {
        return new VariableSetterMethod(clazz, method);
    }

    /**
     * @see ClassCover#getAnnotationClasses()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<Annotation>[] getAnnotationClasses() {
        return new Class[] {Variable.class, VariableParam.class};
    }
    
}
