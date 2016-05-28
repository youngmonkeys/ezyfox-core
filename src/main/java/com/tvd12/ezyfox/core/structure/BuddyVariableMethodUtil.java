package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.BuddyVariableParam;

/**
 * Support to get key and check hidden from {@code BuddyVariable} annotation 
 * or {@code BuddyVariableParam} annotation
 * 
 * @author tavandung12
 *
 */

public class BuddyVariableMethodUtil {

    // prevent new instance
    private BuddyVariableMethodUtil() {}
    
    /**
     * Check value of {@code visible()} in {@code BuddyVariable} annotation
     * 
     * @param field annotated field
     * @param defaultValue default value
     * @return hidden or not
     */
    public static boolean checkHidden(Field field, boolean defaultValue) {
        BuddyVariable variable = field.getAnnotation(BuddyVariable.class);
        return (variable != null) ? !variable.visible() : defaultValue;
    }
    
    /**
     * Check value of {@code visible()} in {@code BuddyVariable} annotation
     * 
     * @param method annotated method
     * @param defaultValue default value
     * @return hidden or not
     */
    public static boolean checkHidden(Method method, boolean defaultValue) {
        BuddyVariable variable = method.getAnnotation(BuddyVariable.class);
        return (variable != null) ? !variable.visible() : defaultValue;
    }
    
    /**
     * Read {@code Variable} and {@code BuddyVariableParam} annotation to get key
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
     * Read {@code Variable} and {@code BuddyVariableParam} annotation to get key
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
     * Read {@code BuddyVariable} annotation to get key
     * 
     * @param field annotated field
     * @return a key as string
     */
    private static String getKeyFromVariableAnnotation(Field field) {
        return getKeyFromVariableAnnotation(
                field.getAnnotation(BuddyVariable.class));
    }
    
    /**
     * Read {@code BuddyVariable} annotation to get key
     * 
     * @param method annotated field
     * @return a key as string
     */
    private static String getKeyFromVariableAnnotation(Method method) {
        return getKeyFromVariableAnnotation(
                method.getAnnotation(BuddyVariable.class));
    }
    
    /**
     * Read {@code BuddyVariable} annotation to get key
     * 
     * @param variable Variable annotation
     * @return a key as string
     */
    private static String getKeyFromVariableAnnotation(BuddyVariable variable) {
        if(variable == null)
            return "";
        String value = variable.value().trim();
        return (value.length() > 0) ? value : variable.name().trim();
    }
    
    /**
     * Read {@code BuddyVariableParam} annotation to get key
     * 
     * @param field annotated field
     * @return a key as string
     */
    private static String getKeyFromVariableParamAnnotation(Field field) {
        return getKeyFromVariableParamAnnotation(
                field.getAnnotation(BuddyVariableParam.class), field.getName());
    }
    
    /**
     * Read {@code BuddyVariableParam} annotation to get key
     * 
     * @param method annotated method
     * @param defaultValue default value
     * @return a key as string
     */
    private static String getKeyFromVariableParamAnnotation(Method method, 
            String defaultValue) {
        return getKeyFromVariableParamAnnotation(
                method.getAnnotation(BuddyVariableParam.class), defaultValue);
    }
    
    /**
     * Read {@code BuddyVariableParam} annotation to get key
     * 
     * @param variable VariableParam annotation
     * @param defaultValue default value
     * @return a key as string
     */
    private static String getKeyFromVariableParamAnnotation(
            BuddyVariableParam variable, String defaultValue) {
        if(variable == null)
            return "";
        String value = variable.value().trim();
        value = (value.length() > 0) ? value : variable.name().trim();
        return (value.length() > 0) ? value : defaultValue;
    }

}
