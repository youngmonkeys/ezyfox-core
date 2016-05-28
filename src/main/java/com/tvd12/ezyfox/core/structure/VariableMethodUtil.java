package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;

/**
 * Support to get key and check hidden from {@code Variable} annotation 
 * or {@code VariableParam} annotation
 * 
 * @author tavandung12
 *
 */

public class VariableMethodUtil {

    // prevent new instance
    private VariableMethodUtil() {}
    
    /**
     * Check value of {@code visible()} in {@code Variable} annotation
     * 
     * @param field annotated field
     * @param defaultValue default value
     * @return hidden or not
     */
    public static boolean checkHidden(Field field, boolean defaultValue) {
        Variable variable = field.getAnnotation(Variable.class);
        return (variable != null) ? !variable.visible() : defaultValue;
    }
    
    /**
     * Check value of {@code visible()} in {@code Variable} annotation
     * 
     * @param method annotated method
     * @param defaultValue default value
     * @return hidden or not
     */
    public static boolean checkHidden(Method method, boolean defaultValue) {
        Variable variable = method.getAnnotation(Variable.class);
        return (variable != null) ? !variable.visible() : defaultValue;
    }
    
    /**
     * Read {@code Variable} and {@code VariableParam} annotation to get key
     * 
     * @param field annotated field
     * @param defaultKey default value
     * @return a key as string
     */
    public static String getKey(Field field, String defaultKey) {
        String value = getKeyFromVariableAnnotation(field);
        value = (value.length() > 0) 
                ? value 
                : getKeyFromVariableParamAnnotation(field);
        return value.length() > 0 ? value : defaultKey;
    }
    
    /**
     * Read {@code Variable} and {@code VariableParam} annotation to get key
     * 
     * @param method annotated method
     * @param defaultKey default value
     * @return a key as string
     */
    public static String getKey(Method method, String defaultKey) {
        String value = getKeyFromVariableAnnotation(method);
        value = (value.length() > 0) 
                ? value 
                : getKeyFromVariableParamAnnotation(method, defaultKey);
        return value.length() > 0 ? value : defaultKey;
    }
    
    /**
     * Read {@code Variable} annotation to get key
     * 
     * @param field annotated field
     * @return a key as string
     */
    private static String getKeyFromVariableAnnotation(Field field) {
        return getKeyFromVariableAnnotation(
                field.getAnnotation(Variable.class));
    }
    
    /**
     * Read {@code Variable} annotation to get key
     * 
     * @param method annotated field
     * @return a key as string
     */
    private static String getKeyFromVariableAnnotation(Method method) {
        return getKeyFromVariableAnnotation(
                method.getAnnotation(Variable.class));
    }
    
    /**
     * Read {@code Variable} annotation to get key
     * 
     * @param variable Variable annotation
     * @return a key as string
     */
    private static String getKeyFromVariableAnnotation(Variable variable) {
        if(variable == null)
            return "";
        String value = variable.value().trim();
        return (value.length() > 0) ? value : variable.name().trim();
    }
    
    /**
     * Read {@code VariableParam} annotation to get key
     * 
     * @param field annotated field
     * @return a key as string
     */
    private static String getKeyFromVariableParamAnnotation(Field field) {
        return getKeyFromVariableParamAnnotation(
                field.getAnnotation(VariableParam.class), field.getName());
    }
    
    /**
     * Read {@code VariableParam} annotation to get key
     * 
     * @param method annotated method
     * @param defaultValue default value
     * @return a key as string
     */
    private static String getKeyFromVariableParamAnnotation(Method method, 
            String defaultValue) {
        return getKeyFromVariableParamAnnotation(
                method.getAnnotation(VariableParam.class), defaultValue);
    }
    
    /**
     * Read {@code VariableParam} annotation to get key
     * 
     * @param variable VariableParam annotation
     * @param defaultValue default value
     * @return a key as string
     */
    private static String getKeyFromVariableParamAnnotation(
            VariableParam variable, String defaultValue) {
        if(variable == null)
            return "";
        String value = variable.value().trim();
        value = (value.length() > 0) ? value : variable.name().trim();
        return (value.length() > 0) ? value : defaultValue;
    }

}
