package com.tvd12.ezyfox.core.annotation.parser;

import static com.tvd12.ezyfox.core.annotation.parser.ParserUtil.isRoomAgentClass;
import static com.tvd12.ezyfox.core.annotation.parser.ParserUtil.isUserAgentClass;
import static com.tvd12.ezyfox.core.reflect.ReflectMethodUtil.getPublicMethodSet;

import java.lang.reflect.Method;
import java.util.Collection;

import com.tvd12.ezyfox.core.annotation.HandleMethod;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * Support to read {@code @HandleMethod} annotation and handle method of server event handler class
 * 
 * @author tavandung12
 *
 */
public final class HandleMethodParser {

    //prevent new instance
	private HandleMethodParser() {}
	
	/**
	 * Obtain handling method of server event handler class
	 * 
	 * @param clazz server event handler class
	 * @param parameterTypes array of parameter types
	 * @return handling method
	 */
	public static Method getServerHandleMethod(Class<?> clazz,
			Class<?>... parameterTypes) {
		int length = parameterTypes.length;
		for(Method method : getPublicMethodSet(clazz)) {
			if(!validateMethod(method, length)) 
				continue;
			for(int i = 0 ; i < length ; i++) {
				if(parameterTypes[i] != method.getParameterTypes()[i])
					continue;
				return method;
			}
		}
		throw new RuntimeException("Has no handle method in class " + clazz);
	}
	
	/**
	 * Check whether method be annotated with {@code HandleMethod} annotation or default fuction or not
	 * 
	 * @param method method to check
	 * @param numberOfParams number of parameters
	 * @return true or false
	 */
	private static boolean validateMethod(Method method, int numberOfParams) {
	    return (method.isAnnotationPresent(HandleMethod.class)
	            || "handle".equals(method.getName()))
	            && method.getParameterTypes().length == numberOfParams;
	}
	
	/**
	 * Obtain handling method of server event handler class
	 * 
	 * @param clazz server event handler class
	 * @param userClass user agent class
	 * @param roomClasses list of room agent classes
	 * @param gameUserClasses list of game user agent classes
	 * @return handling method
	 */
	public static Method getServerHandleMethod(Class<?> clazz, 
	        Collection<Class<?>> roomClasses, 
	        Class<?> userClass, Collection<Class<?>> gameUserClasses) {
	    Method handleMethod = getServerHandleMethod(
	            getPublicMethodSet(clazz), roomClasses, userClass, gameUserClasses);
	    if(handleMethod != null)
	        return handleMethod;
	    throw new RuntimeException("Has no handle methods on class " + clazz);
	    
	}
	
	/**
	 * Obtain handling method of server event handler class
	 * 
	 * @param methods list of method to check
	 * @param roomClasses list of room agent classes
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return handling method
	 */
	private static Method getServerHandleMethod(
	        Collection<Method> methods, 
	        Collection<Class<?>> roomClasses, 
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        for(Method method : methods) {
            if(validateMethod(method)
                    && method.getParameterTypes().length == 3
                    && validateFirstParamType(method)
                    && validateSecondParamType(method, roomClasses)
                    && validateThirdParamType(method, userClass, gameUserClasses)) {
                return method;
            }
        }
        return null;
    }
	
	/**
	 * Check whether method be annotated {@code @HandleMethod} annotation or default method or not
	 * 
	 * @param method method to check
	 * @return true or false
	 */
	private static boolean validateMethod(Method method) {
	    return method.isAnnotationPresent(HandleMethod.class)
	            || "handle".equals(method.getName());
	}
	
	/**
	 * Check whether first parameter of method is {@code AppContext} or not
	 * 
	 * @param method method to check
	 * @return true or false
	 */
	private static boolean validateFirstParamType(Method method) {
	    return method.getParameterTypes()[0] == AppContext.class;
	}
	
	/**
	 * Check whether second parameter of method is a room agent class or not
	 * 
	 * @param method method to check
	 * @param roomClasses list of room agent classes
	 * @return true or false
	 */
	private static boolean validateSecondParamType(Method method, Collection<Class<?>> roomClasses) {
	    return isRoomAgentClass(roomClasses, method.getParameterTypes()[1]);
	}
	
	/**
	 * Check third parameter of method is user agent class or game user agent class or not
	 * 
	 * @param method method to check
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return true or false
	 */
	private static boolean validateThirdParamType(Method method,
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        Class<?> type = method.getParameterTypes()[2];
        return isUserAgentClass(type, userClass, gameUserClasses);
    }
	
	/**
	 * Get that handle method contains a model class and user agent class or game user agent class
	 * 
	 * @param clazz declaring class of handle method
	 * @param modelClass model's class
	 * @param userClass user agent's class
	 * @param gameUserClasses game user agent's class
	 * @return handle method 
	 */
	public static Method getServerHandleMethod(Class<?> clazz, 
	        Class<?> modelClass,
	        Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        Method handleMethod = getServerHandleMethod(
                getPublicMethodSet(clazz), modelClass, userClass, gameUserClasses);
        if(handleMethod != null)
            return handleMethod;
        throw new RuntimeException("Has no handle methods on class " + clazz);
        
    }
    
	/**
	 * Check and get handle method in method list
	 * 
	 * @param methods method list
	 * @param modelClass model's class
	 * @param userClass user agent's class
	 * @param gameUserClasses game user agent's class
	 * @return handle method
	 */
    private static Method getServerHandleMethod(Collection<Method> methods, 
            Class<?> modelClass, 
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        for(Method method : methods) {
            if(validateMethod(method)
                    && method.getParameterTypes().length == 3
                    && validateFirstParamType(method)
                    && validateSecondParamType(method, modelClass)
                    && validateThirdParamType(method, userClass, gameUserClasses)) {
                return method;
            }
        }
        return null;
    }
	
    /**
     * Check if second parameter of method is model's class
     * 
     * @param method method to check
     * @param modelClass model's class
     * @return true or false
     */
	private static boolean validateSecondParamType(Method method, Class<?> modelClass) {
	    return method.getParameterTypes()[1] == modelClass;
	}
	
	/**
	 * Obtain handling method of server event handler class
	 * 
	 * @param clazz server event handler class
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return handling method
	 */
	public static Method getServerHandleMethod(Class<?> clazz, 
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        Method handleMethod = getServerHandleMethod(
                getPublicMethodSet(clazz), userClass, gameUserClasses);
        if(handleMethod != null)
            return handleMethod;
        throw new RuntimeException("Has no handle methods on class " + clazz);
        
    }
    
	/**
	 * Obtain handling method of server event handler class
	 * 
	 * @param methods list of methods in handler class
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return handling method
	 */
    private static Method getServerHandleMethod(Collection<Method> methods, 
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        for(Method method : methods) {
            if(validateMethod(method)
                    && method.getParameterTypes().length == 2
                    && validateFirstParamType(method)
                    && validateSecondParamType(method, userClass, gameUserClasses)) {
                return method;
            }
        }
        return null;
    }
    
    /**
     * Check whether second parameter of method is user agent class or game user agent class or not
     * 
     * @param method method to check
     * @param userClass user agent class
     * @param gameUserClasses list of game user agent classes
     * @return true or false
     */
    private static boolean validateSecondParamType(Method method,
            Class<?> userClass, Collection<Class<?>> gameUserClasses) {
        Class<?> type = method.getParameterTypes()[1];
        return isUserAgentClass(type, userClass, gameUserClasses);
    }
}
