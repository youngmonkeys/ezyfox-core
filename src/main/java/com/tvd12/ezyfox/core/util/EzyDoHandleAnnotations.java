package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyDoHandle;

public final class EzyDoHandleAnnotations {

    private EzyDoHandleAnnotations() {}
    
    public static String getCommand(EzyDoHandle annotation) {
        if(EzyInternalStrings.getInstance().isNoContent(annotation.value()))
            return annotation.command();
        return annotation.value();
    }
    
}
