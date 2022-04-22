package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyRequestController;

public final class EzyRequestControllerAnnotations {

    private EzyRequestControllerAnnotations() {}

    public static String getGroup(EzyRequestController annotation) {
        String group = annotation.value();
        if (EzyInternalStrings.getInstance().isNoContent(group)) {
            group = annotation.group();
        }
        return group;
    }

    public static String getGroup(Class<?> controllerClass) {
        EzyRequestController annotation = controllerClass.getAnnotation(EzyRequestController.class);
        return getGroup(annotation);
    }
}
