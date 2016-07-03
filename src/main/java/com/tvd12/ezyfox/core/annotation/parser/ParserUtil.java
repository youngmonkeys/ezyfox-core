/**
 * 
 */
package com.tvd12.ezyfox.core.annotation.parser;

import java.util.Collection;

import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;

/**
 * @author tavandung12
 *
 */
public class ParserUtil {

    private ParserUtil() {}
    
    public static boolean isUserAgentClass(Class<?> paramType, 
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        return paramType == ApiUser.class ||
                paramType == userClass || 
                gameUserClasses.contains(paramType);
    }
    
    public static boolean isRoomAgentClass(Collection<Class<?>> roomClasses,
            Class<?> paramType) {
        return paramType == ApiRoom.class || roomClasses.contains(paramType);
    }
}
