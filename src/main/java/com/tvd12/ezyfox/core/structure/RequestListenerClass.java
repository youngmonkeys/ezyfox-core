package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.RequestParam;

/**
 * Hold structure of request listener class
 * 
 * @author tavandung12
 *
 */

public class RequestListenerClass extends ClassWrapper {
    
    // prevent new instance with default constructor
    private RequestListenerClass() {}
    
    /**
     * @param clazz the class to parse
     */
	public RequestListenerClass(Class<?> clazz) {
		super(clazz);
	}
	
	/**
	 * Create an instance
	 * 
	 * @see ClassWrapper#newClass()
	 */
	@Override
	protected ClassWrapper newClass() {
		return new RequestListenerClass();
	}
	
	/**
	 * @see ClassWrapper#initWithField(Field)
	 */
	@Override
	protected SetterMethodCover initWithField(Field field) {
		return new RequestParamMethod(clazz, field);
	}

	/**
	 * @see ClassWrapper#initWithMethod(Method)
	 */
	@Override
	protected SetterMethodCover initWithMethod(Method method) {
		return new RequestParamMethod(clazz, method);
	}

	/**
	 * @see ClassWrapper#getAnnotationClasses()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Class<RequestParam>[] getAnnotationClasses() {
	    return new Class[] {RequestParam.class};
	}

}
