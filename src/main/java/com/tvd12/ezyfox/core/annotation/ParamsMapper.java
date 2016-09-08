/**
 * 
 */
package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation to register mapper of class that contains parameters to response to client
 * 
 * @author tavandung12
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ParamsMapper {

    /**
     * @return serializer class
     */
    public Class<?> serializer() default Class.class;
    
    /**
     * @return deserializer class
     */
    public Class<?> deserializer() default Class.class;
    
}
