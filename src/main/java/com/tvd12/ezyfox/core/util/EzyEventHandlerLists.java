package com.tvd12.ezyfox.core.util;

import static com.tvd12.ezyfox.core.util.EzyEventHandlerAnnotations.getHandlerPriority;

import java.util.List;

public final class EzyEventHandlerLists {

    private EzyEventHandlerLists() {}
    
    public static void sortEventHandlersByPriority(List<Object> handlers) {
        handlers.sort((a, b) ->
            getHandlerPriority(a) - getHandlerPriority(b)
        );
    }
}
