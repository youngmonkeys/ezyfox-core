package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyServerEventHandler;

public final class EzyServerEventHandlerAnnotations {

	private EzyServerEventHandlerAnnotations() {
	}
	
	public static String getEvent(EzyServerEventHandler annotation) {
		String event = annotation.value();
		if(event.isEmpty())
			event = annotation.event();
		return event;
	}
	
}
