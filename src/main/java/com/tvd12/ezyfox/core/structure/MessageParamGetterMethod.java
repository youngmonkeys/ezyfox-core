package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.MessageParam;

/**
 * Support to hold structure of a method that's annotated with {@code MessageParam} annotation
 * 
 * @author tavandung12
 *
 */

public class MessageParamGetterMethod extends GetterMethodCover {
    
    /**
     * @param clazz the return type
     * @param field the java field
     */
    public MessageParamGetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
    }
    
    /**
     * @param clazz the return type
     * @param method the java method
     */
    public MessageParamGetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Method)
     */
    @Override
    protected String getKey(Method method) {
        String value = method.isAnnotationPresent(MessageParam.class) 
                ? method.getAnnotation(MessageParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(method);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Field)
     */
    @Override
    protected String getKey(Field field) {
        String value = field.isAnnotationPresent(MessageParam.class) 
                ? field.getAnnotation(MessageParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(field);
    }
}
