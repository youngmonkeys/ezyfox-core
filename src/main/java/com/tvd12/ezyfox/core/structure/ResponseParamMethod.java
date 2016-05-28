package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.ResponseParam;

/**
 * Support to hold structure of a method that's annotated with {@code ResponseParam} annotation
 * 
 * @author tavandung12
 *
 */

public class ResponseParamMethod extends GetterMethodCover {
    
    /**
     * @see GetterMethodCover#GetterMethodCover(Class, Field) 
     */
    public ResponseParamMethod(Class<?> clazz, Field field) {
        super(clazz, field);
    }
    
    /**
     * @see GetterMethodCover#initWithMethod(Class, Method) 
     */
    public ResponseParamMethod(Class<?> clazz, Method method) {
        super(clazz, method);
    }

    /**
     * @see GetterMethodCover#getKey(Method)
     */
    @Override
    protected String getKey(Method method) {
        String value = method.isAnnotationPresent(ResponseParam.class) 
                ? method.getAnnotation(ResponseParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(method);
    }

    /**
     * @see GetterMethodCover#getKey(Field)
     */
    @Override
    protected String getKey(Field field) {
        String value = field.isAnnotationPresent(ResponseParam.class) 
                ? field.getAnnotation(ResponseParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(field);
    }
}
