package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyRequestInterceptor;

public final class EzyRequestInterceptorAnnotations {

    private EzyRequestInterceptorAnnotations() {}

    public static int getPriority(Class<?> interceptorClass) {
        if (interceptorClass.isAnnotationPresent(EzyRequestInterceptor.class)) {
            return interceptorClass.getAnnotation(EzyRequestInterceptor.class).priority();
        }
        return 0;
    }
}
