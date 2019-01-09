package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestListener;

public final class EzyClientRequestListenerAnnotations {

	private EzyClientRequestListenerAnnotations() {
	}
	
	public static String getCommand(EzyClientRequestListener annotation) {
		String cmd = annotation.value();
		if(cmd.isEmpty())
			cmd = annotation.command();
		return cmd;
	}
	
}
