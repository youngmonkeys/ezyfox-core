package com.tvd12.ezyfox.core.structure;

import static org.reflections.ReflectionUtils.withModifier;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.ReflectionUtils;

import com.google.common.base.Predicates;
import com.tvd12.ezyfox.core.annotation.MessageParam;

/**
 * Support to hold structure of class that it's instance holds data to response to client.
 * Class must be annotated with {@code MessageParam} annotations
 * 
 * @author tavandung12
 *
 */

public class MessageParamsClassUnwrapper extends ClassUnwrapper {

    // prevent new instance with default constructor
    protected MessageParamsClassUnwrapper() {}
    
    /**
     * Construct with annotated class
     * 
     * @param clazz annotated class
     */
    public MessageParamsClassUnwrapper(Class<?> clazz) {
        super(clazz);
    }
    
    /**
     * Create new instance
     * 
     * @see ClassUnwrapper#newClass()
     */
    @Override
    protected ClassUnwrapper newClass() {
        return new MessageParamsClassUnwrapper();
    }
    
    /**
     * @see ClassUnwrapper#initWithField(Field)
     */
    @Override
    protected GetterMethodCover initWithField(Field field) {
        return new MessageParamGetterMethod(clazz, field);
    }

    /**
     * @see ClassUnwrapper#initWithMethod(Method)
     */
    @Override
    protected GetterMethodCover initWithMethod(Method method) {
        return new MessageParamGetterMethod(clazz, method);
    }

    /**
     * @see ClassUnwrapper#getAnnotationClasses()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Class<MessageParam>[] getAnnotationClasses() {
        return new Class[] {MessageParam.class};
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.structure.ClassCover#getAnnotatedFields()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<Field> getAnnotatedFields() {
        if(isWrapper())
            return new ArrayList<Field>(ReflectionUtils
                    .getAllFields(clazz, Predicates.not(
                            withModifier(Modifier.FINAL))));
        return super.getAnnotatedFields();
    }
    
    /* (non-Javadoc)
     * @see com.lagente.core.structure.ClassCover#getAnnotatedMethods()
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<Method> getAnnotatedMethods() {
        if(isWrapper())
            return new ArrayList<Method>(ReflectionUtils
                    .getAllMethods(clazz, withModifier(Modifier.PUBLIC)));
        return super.getAnnotatedMethods();
    }
    
    /**
     * Check whether a class is annotated with {@code MessageParam} annotations
     * @return
     */
    private boolean isWrapper() {
        List<Field> fields = FieldUtils
                .getFieldsListWithAnnotation(clazz, MessageParam.class);
        List<Method> methods = MethodUtils
                .getMethodsListWithAnnotation(clazz, MessageParam.class);
        return fields.isEmpty() && methods.isEmpty();
    }

}
