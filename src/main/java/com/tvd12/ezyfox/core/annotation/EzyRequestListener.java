package com.tvd12.ezyfox.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a class listener a request from client and process that request.
 *
 * @author tavandung12
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EzyRequestListener {

    /**
     * Command of request.
     *
     * @return command of request
     */
    String command() default "";

    /**
     * Command of request.
     *
     * @return the command
     */
    String value() default "";

    /**
     * priority of listener, lowest is first.
     *
     * @return priority of listener
     */
    int priority() default 0;
}
