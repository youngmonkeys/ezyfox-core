package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.ResponseParam;

/**
 * With client request listener class, sometimes we want to response to client, and this class
 * support for this, it holds structure of all methods be annotated with {@code ResponseParam}
 * annotation
 * 
 * @author tavandung12
 *
 */

public class ResponseHandlerClass extends ClassUnwrapper {

    // prevent new instance with default constructor
    private ResponseHandlerClass() {}
    
    /**
     * @see ClassUnwrapper#ClassUnwrapper(Class)
     */
    public ResponseHandlerClass(Class<?> clazz) {
        super(clazz);
    }
    
    /**
     * Create new instance
     * 
     * @see ClassWrapper#newClass()
     */
    @Override
    protected ClassUnwrapper newClass() {
        return new ResponseHandlerClass();
    }
    
    /**
     * @see ClassWrapper#initWithField(Field)
     */
    @Override
    protected GetterMethodCover initWithField(Field field) {
        return new ResponseParamMethod(clazz, field);
    }

    /**
     * @see ClassWrapper#initWithMethod(Method)
     */
    @Override
    protected GetterMethodCover initWithMethod(Method method) {
        return new ResponseParamMethod(clazz, method);
    }

    /**
     * @see ClassWrapper#getAnnotationClasses()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<ResponseParam>[] getAnnotationClasses() {
        return new Class[] {ResponseParam.class};
    }

}
