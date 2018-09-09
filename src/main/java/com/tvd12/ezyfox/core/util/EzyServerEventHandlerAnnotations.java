package com.tvd12.ezyfox.core.util;

import org.apache.commons.lang3.StringUtils;

import com.tvd12.ezyfox.core.annotation.EzyServerEventHandler;

public final class EzyServerEventHandlerAnnotations {

	private EzyServerEventHandlerAnnotations() {
	}
	
	public static String getEvent(EzyServerEventHandler annotation) {
		String event = annotation.value();
		if(StringUtils.isEmpty(event))
			event = annotation.event();
		return event;
	}
	
}
