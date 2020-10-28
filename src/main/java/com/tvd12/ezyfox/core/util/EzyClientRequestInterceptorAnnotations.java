package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestInterceptor;

public final class EzyClientRequestInterceptorAnnotations {

	private EzyClientRequestInterceptorAnnotations() {}
	
	public static int getPriority(Class<?> interceptorClass) {
		if(interceptorClass.isAnnotationPresent(EzyClientRequestInterceptor.class))
			return interceptorClass.getAnnotation(EzyClientRequestInterceptor.class).priority();
		return 0;
	}
	
}
