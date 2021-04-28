package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyRequestListener;

public final class EzyRequestListenerAnnotations {

	private EzyRequestListenerAnnotations() {}
	
	public static String getCommand(EzyRequestListener annotation) {
		String cmd = annotation.value();
		if(cmd.isEmpty())
			cmd = annotation.command();
		return cmd;
	}
	
}
