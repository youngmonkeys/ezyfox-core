package com.tvd12.ezyfox.core.structure;

import static com.tvd12.ezyfox.core.structure.BuddyVariableMethodUtil.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Support to hold structure of method be annotated with {@code Variable} or {@code VariableParam}
 * annotation
 * 
 * @author tavandung12
 *
 */

public class BuddyVariableGetterMethod extends GetterMethodCover {
    
    /**
     * @see GetterMethodCover#GetterMethodCover(Class, Field)
     */
    public BuddyVariableGetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
        this.isHidden = checkHidden(field, isHidden);
    }
    
    /**
     * @see GetterMethodCover#GetterMethodCover(Class, Method)
     */
    public BuddyVariableGetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
        this.isHidden = checkHidden(method, isHidden);
    }
    
    /**
     * @see GetterMethodCover#getKey(Field)
     */
    @Override
    protected String getKey(Field field) {
        return BuddyVariableMethodUtil.getKey(field, super.getKey(field));
    }
    
    /**
     * @see GetterMethodCover#getKey(Method)
     */
    @Override
    protected String getKey(Method method) {
        return BuddyVariableMethodUtil.getKey(method, super.getKey(method));
    }
}
