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
     * @param clazz the return type
     * @param field the java field
     */
    public ResponseParamMethod(Class<?> clazz, Field field) {
        super(clazz, field);
    }
    
    /**
     * @param clazz the return type
     * @param method the java method
     */
    public ResponseParamMethod(Class<?> clazz, Method method) {
        super(clazz, method);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Method)
     */
    @Override
    protected String getKey(Method method) {
        String value = method.isAnnotationPresent(ResponseParam.class) 
                ? method.getAnnotation(ResponseParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(method);
    }

    /*
     * (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.MethodCover#getKey(java.lang.reflect.Field)
     */
    @Override
    protected String getKey(Field field) {
        String value = field.isAnnotationPresent(ResponseParam.class) 
                ? field.getAnnotation(ResponseParam.class).value().trim()
                : "";
        return value.length() > 0 ? value : super.getKey(field);
    }
}
