package com.tvd12.ezyfox.core.structure;

import static com.tvd12.ezyfox.core.structure.BuddyVariableMethodUtil.checkHidden;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Support to hold structure of method be annotated with {@code Variable} or {@code VariableParam}
 * annotation
 * 
 * @author tavandung12
 *
 */

public class BuddyVariableSetterMethod extends SetterMethodCover {
    
    /**
     * @see SetterMethodCover#SetterMethodCover(Class, Field)
     */
    public BuddyVariableSetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
        this.isHidden = checkHidden(field, isHidden);
    }
    
    /**
     * @see SetterMethodCover#SetterMethodCover(Class, Method)
     */
    public BuddyVariableSetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
        this.isHidden = checkHidden(method, isHidden);
    }
    
    /**
     * @see SetterMethodCover#getKey(Field)
     */
    @Override
    protected String getKey(Field field) {
        return BuddyVariableMethodUtil.getKey(field, super.getKey(field));
    }
    
    /**
     * @see SetterMethodCover#getKey(Method)
     */
    @Override
    protected String getKey(Method method) {
        return BuddyVariableMethodUtil.getKey(method, super.getKey(method));
    }
}
