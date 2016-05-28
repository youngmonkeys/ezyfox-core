package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Indicates that a class handle room event with room name.
 * To be used conjunction with {@code @ServerEventHandler}
 * 
 * @author tavandung12
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE })
public @interface RoomName {
    
    /**
     * name of room
     * 
     * @return name of room
     */
    public String value();
}
