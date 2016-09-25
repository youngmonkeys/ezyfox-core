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
     * @param clazz the return type of java method
     * @param field the java field
     */
    public BuddyVariableGetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
        this.isHidden = checkHidden(field, isHidden);
    }
    
    /**
     * @param clazz the return type of java method
     * @param method the java method
     */
    public BuddyVariableGetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
        this.isHidden = checkHidden(method, isHidden);
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Field)
     */
    @Override
    protected String getKey(Field field) {
        return BuddyVariableMethodUtil.getKey(field, super.getKey(field));
    }
    
    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Method)
     */
    @Override
    protected String getKey(Method method) {
        return BuddyVariableMethodUtil.getKey(method, super.getKey(method));
    }
}
