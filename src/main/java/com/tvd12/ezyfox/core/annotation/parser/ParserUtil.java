/**
 * 
 */
package com.tvd12.ezyfox.core.annotation.parser;

import java.util.Collection;

/**
 * @author tavandung12
 *
 */
public class ParserUtil {

    private ParserUtil() {}
    
    public static boolean isUserAgentClass(Class<?> paramType, 
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        return paramType.isAssignableFrom(userClass) || 
                isGameUserAgentClass(paramType, gameUserClasses);
    }
    
    public static boolean isGameUserAgentClass(Class<?> paramType,
            Collection<Class<?>> gameUserClasses) {
        for(Class<?> guclass : gameUserClasses) {
            if(paramType.isAssignableFrom(guclass))
                return true;
        }
        return false;
    }
    
    public static boolean isRoomAgentClass(Collection<Class<?>> roomClasses,
            Class<?> paramType) {
        for(Class<?> rclass : roomClasses) {
            if(paramType.isAssignableFrom(rclass))
                return true;
        }
        return false;
    }
}
