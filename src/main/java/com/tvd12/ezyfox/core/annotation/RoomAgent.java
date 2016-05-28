package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Indicates that a class is a room agent
 * 
 * @author tavandung12
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE })
public @interface RoomAgent {
    
    /**
     * a class represents to room agent
     * 
     * @return a class object
     */
    public Class<?> clazz() default Class.class;
}
