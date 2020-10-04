package com.tvd12.ezyfox.core.util;

class EzyInternalStrings {
	
	private static final EzyInternalStrings INSTANCE = new EzyInternalStrings();
	
	private EzyInternalStrings() {}
	
	public static EzyInternalStrings getInstance() {
		return INSTANCE;
	}

	public boolean isNoContent(String cs) {
        boolean noContent = (cs.isEmpty() || cs.trim().isEmpty());
        return noContent;
    }
	
}
