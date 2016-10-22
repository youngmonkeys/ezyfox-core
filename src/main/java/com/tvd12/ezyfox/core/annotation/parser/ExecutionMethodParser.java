package com.tvd12.ezyfox.core.annotation.parser;

import static com.tvd12.ezyfox.core.reflect.ReflectMethodUtil.getPublicMethodSet;

import java.lang.reflect.Method;
import java.util.Collection;

import com.tvd12.ezyfox.core.annotation.ExecuteMethod;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * Support to read {@code @ExecuteMethod} annotation and execute method of client request listener class
 * 
 * @author tavandung12
 *
 */
public final class ExecutionMethodParser {

    // prevent new instance
	private ExecutionMethodParser() {}
	
	/**
	 * Get execute method of listener class
	 * 
	 * @param clazz listener class
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return execute method
	 */
	public static Method getListenerExecuteMethod(Class<?> clazz, 
	        Class<?> userClass, Collection<Class<?>> gameUserClasses) {
		Method executeMethod = getListenerExecuteMethod(
		        getPublicMethodSet(clazz), userClass, gameUserClasses);
		if(executeMethod != null)
		    return executeMethod;
		throw new RuntimeException("Has no execution method in class " + clazz);
	}
	
	/**
	 * Obtain executive method of listener class
	 * 
	 * @param methods list of methods in listener class
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return
	 */
	private static Method getListenerExecuteMethod(Collection<Method> methods,
	        Class<?> userClass, Collection<Class<?>> gameUserClasses) {
	    for(Method method : methods) {
            if(validateMethod(method)
                    && method.getParameterTypes().length == 2
                    && validateFirstParamType(method)
                    && validateSecondParamType(method, userClass, gameUserClasses)) 
                return method;
        }
	    return null;
	}
	
	/**
	 * Check whether method be annotated with {@code @ExecuteMethod}
	 * 
	 * @param method method to check
	 * @return true or false
	 */
	private static boolean validateMethod(Method method) {
	    return method.isAnnotationPresent(ExecuteMethod.class)
	            || "execute".equals(method.getName());
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
	 * Check whether second parameter of method is user agent class or game user agent class
	 * 
	 * @param method method to check
	 * @param userClass user agent class
	 * @param gameUserClasses list of game user agent classes
	 * @return true or false
	 */
	private static boolean validateSecondParamType(Method method,
	        Class<?> userClass, Collection<Class<?>> gameUserClasses) {
	    Class<?> type = method.getParameterTypes()[1];
	    return ParserUtil.isUserAgentClass(type, userClass, gameUserClasses);
	}
	
}
