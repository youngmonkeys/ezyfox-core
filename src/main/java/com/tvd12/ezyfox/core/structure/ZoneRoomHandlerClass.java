/**
 * 
 */
package com.tvd12.ezyfox.core.structure;

import static com.tvd12.ezyfox.core.annotation.parser.ParserUtil.isRoomAgentClass;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import com.tvd12.ezyfox.core.annotation.HandleMethod;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

/**
 * @author tavandung12
 *
 */
public class ZoneRoomHandlerClass extends ServerHandlerClass {

    /**
     * @param clazz the class to parse
     * @param roomClasses the array of room classes
     */
    public ZoneRoomHandlerClass(Class<?> clazz, Class<?>[] roomClasses) {
        super(clazz, roomClasses);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.ServerHandlerClass#checkHandleMethod(java.lang.Class, java.lang.Class[])
     */
    @Override
    protected void checkHandleMethod(Class<?> clazz, Class<?>... roomClasses) {
        Collection<Class<?>> roomClassColl = Arrays.asList(roomClasses);
        Set<Method> methods = ReflectMethodUtil.getPublicMethodSet(clazz);
        for(Method method : methods) {
            if(validateHandleMethod(method, roomClassColl)) {
                handleMethod = method; return;
            } else continue;
        }
        throw new RuntimeException("Has no handle method in class " + clazz);
    }
    
    private boolean validateHandleMethod(Method method, Collection<Class<?>> roomClasses) {
        boolean validMethod = method.getName().equals("handle") ||
                method.isAnnotationPresent(HandleMethod.class);
        boolean validParam = method.getParameterTypes().length == 3 &&
                method.getParameterTypes()[0] == AppContext.class &&
                method.getParameterTypes()[1] == ApiZone.class &&
                isRoomAgentClass(roomClasses, method.getParameterTypes()[2]);
        return validMethod && validParam;
    }
    
}
