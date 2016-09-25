/**
 * 
 */
package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.reflections.ReflectionUtils;

import com.tvd12.ezyfox.core.annotation.ExecuteMethod;

/**
 * @author tavandung12
 *
 */
public class RoomRequestResponseClass extends RequestResponseClass {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.RequestResponseClass#fetchUserClass()
     */
    @Override
    protected Class<?> fetchUserClass() {
        return executeMethod.getParameterTypes()[2];
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.structure.RequestResponseClass#checkExecuteMethod(java.lang.Class, java.lang.Class, java.util.List)
     */
    @Override
    public void checkExecuteMethod(Class<?> userClazz, List<Class<?>> gameUserClasses) {
        executeMethod = getExecutionMethod(getClazz());
    }
    
    @SuppressWarnings("unchecked")
    private Method getExecutionMethod(Class<?> clazz) {
        Method answer = null;
        Set<Method> allMethods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(ExecuteMethod.class));
        if(allMethods.size() == 0) 
            allMethods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withName("execute"));
        if(allMethods.size() > 0)
            answer = allMethods.iterator().next();
        if(answer != null && answer.getParameterTypes().length == 3)
            return answer;
        throw new IllegalStateException("Has no execute method in " + clazz + ", make sure you have an execute method with three paramerters (AppContext, Room, User)");
    }
    
}
