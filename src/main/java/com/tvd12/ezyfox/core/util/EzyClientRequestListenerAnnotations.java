package com.tvd12.ezyfox.core.util;

import org.apache.commons.lang3.StringUtils;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestListener;

public final class EzyClientRequestListenerAnnotations {

	private EzyClientRequestListenerAnnotations() {
	}
	
	public static String getCommand(EzyClientRequestListener annotation) {
		String cmd = annotation.value();
		if(StringUtils.isEmpty(cmd))
			cmd = annotation.command();
		return cmd;
	}
	
}
