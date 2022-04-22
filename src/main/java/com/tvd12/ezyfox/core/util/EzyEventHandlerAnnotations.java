package com.tvd12.ezyfox.core.util;

import com.tvd12.ezyfox.core.annotation.EzyEventHandler;

public final class EzyEventHandlerAnnotations {

    private EzyEventHandlerAnnotations() {}
    
    public static String getEvent(EzyEventHandler annotation) {
        String event = annotation.value();
        if(event.isEmpty())
            event = annotation.event();
        return event;
    }
    
    public static int getHandlerPriority(Object handler) {
        EzyEventHandler anno = handler
            .getClass()
            .getAnnotation(EzyEventHandler.class);
        return anno != null ? anno.priority() : 0;
    }
}
