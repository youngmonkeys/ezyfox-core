package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyRequestHandle;

public final class EzyRequestHandleAnnotations {

	private EzyRequestHandleAnnotations() {}
	
	public static String getCommand(EzyRequestHandle annotation) {
		if(EzyInternalStrings.getInstance().isNoContent(annotation.value()))
			return annotation.command();
		return annotation.value();
	}
	
}
