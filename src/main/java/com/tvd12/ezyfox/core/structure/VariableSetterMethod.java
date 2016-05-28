package com.tvd12.ezyfox.core.structure;

import static com.tvd12.ezyfox.core.structure.VariableMethodUtil.checkHidden;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 
 * Support to hold structure of setter method that's annotated with {@code Variable} 
 * or {@code VariableParam} annotation
 * 
 * @author tavandung12
 *
 */
public class VariableSetterMethod extends SetterMethodCover {
    
    /**
     * Construct with class and field
     * 
     * @param clazz declared class of field
     * @param field field to get setter method
     */
    public VariableSetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
        this.isHidden = checkHidden(field, isHidden);
    }
    
    /**
     * Construct with class and setter method
     * 
     * @param clazz declared class of method
     * @param method setter method
     */
    public VariableSetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
        this.isHidden = checkHidden(method, isHidden);
    }
    
    /**
     * @see SetterMethodCover#getKey(Field)
     */
    @Override
    protected String getKey(Field field) {
        return VariableMethodUtil.getKey(field, super.getKey(field));
    }
    
    /**
     * @see SetterMethodCover#getKey(Method)
     */
    @Override
    protected String getKey(Method method) {
        return VariableMethodUtil.getKey(method, super.getKey(method));
    }
    
}
