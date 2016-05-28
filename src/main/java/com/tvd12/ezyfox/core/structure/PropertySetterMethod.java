package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;

import com.tvd12.ezyfox.core.annotation.ConfigProperty;

/**
 * Hold structure of method be annotated with {@code ConfigProperty} annotation
 * 
 * @see SetterMethodCover
 * 
 * @author tavandung12
 *
 */

public class PropertySetterMethod extends SetterMethodCover {
    
    /**
     * @see SetterMethodCover#SetterMethodCover(Class, Field)
     */
    public PropertySetterMethod(Class<?> clazz, Field field) {
        super(clazz, field);
    }

    /**
     * @see SetterMethodCover#getKey(Field)
     */
    @Override
    protected String getKey(Field field) {
        String key = field.getAnnotation(ConfigProperty.class).value().trim();
        return (key.length() > 0) ? key : super.getKey(field);
    }
    
}
