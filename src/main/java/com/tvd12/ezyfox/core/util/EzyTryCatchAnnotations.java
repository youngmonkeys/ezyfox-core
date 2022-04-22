package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyTryCatch;

public final class EzyTryCatchAnnotations {

    private EzyTryCatchAnnotations() {}

    public static Class<?>[] getExceptionClasses(EzyTryCatch tryCatch) {
        return tryCatch.value();
    }
}
