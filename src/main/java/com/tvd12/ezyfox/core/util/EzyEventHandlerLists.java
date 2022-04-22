package com.tvd12.ezyfox.core.util;

import java.util.Comparator;
import java.util.List;

public final class EzyEventHandlerLists {

    private EzyEventHandlerLists() {}

    public static void sortEventHandlersByPriority(List<Object> handlers) {
        handlers.sort(Comparator.comparingInt(EzyEventHandlerAnnotations::getHandlerPriority));
    }
}
