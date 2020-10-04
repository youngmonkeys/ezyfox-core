package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestController;

public final class EzyClientRequestControllerAnnotations {

	private EzyClientRequestControllerAnnotations() {}

	public static String getGroup(EzyClientRequestController annotation) {
		String group = annotation.value();
		if(EzyInternalStrings.getInstance().isNoContent(group))
			group = annotation.group();
		return group;
	}
	
	public static String getGroup(Class<?> controllerClass) {
		EzyClientRequestController annotation = controllerClass.getAnnotation(EzyClientRequestController.class);
		return getGroup(annotation);
	}
	
}
