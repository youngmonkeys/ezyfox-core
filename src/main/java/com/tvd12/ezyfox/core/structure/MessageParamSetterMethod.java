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

public class MessageParamSetterMethod extends SetterMethodCover {
    
    /**
     * @see SetterMethodCover#SetterMethodCover(Class, Field) 
     */
    public MessageParamSetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
    }
    
    /**
     * @see SetterMethodCover#initWithMethod(Class, Method) 
     */
    public MessageParamSetterMethod(Class<?> clazz, Method method) {
        super(clazz, method);
    }

    /**
     * @see SetterMethodCover#getKey(Method)
     */
    @Override
    protected String getKey(Method method) {
        String value = method.isAnnotationPresent(MessageParam.class) 
                ? method.getAnnotation(MessageParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(method);
    }

    /**
     * @see SetterMethodCover#getKey(Field)
     */
    @Override
    protected String getKey(Field field) {
        String value = field.isAnnotationPresent(MessageParam.class) 
                ? field.getAnnotation(MessageParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(field);
    }
}
