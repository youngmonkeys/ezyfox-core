package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a class handle an event related to a zone with name.
 * To be used conjunction with {@code @ServerEventHanlder}
 * 
 * @author tavandung12
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE })
public @interface ZoneName {
    
    /**
     * name of zone
     * 
     * @return name of zone
     */
    public String value();
}
