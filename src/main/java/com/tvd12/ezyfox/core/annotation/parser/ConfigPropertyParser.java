package com.tvd12.ezyfox.core.annotation.parser;

import static com.tvd12.ezyfox.core.reflect.ReflectConvertUtil.stringToChar;

import java.util.Properties;

import com.tvd12.ezyfox.core.structure.ClassWrapper;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;

/**
 * Support to read properties object and map value to a object
 * 
 * @author tavandung12
 *
 */
public final class ConfigPropertyParser {

    // prevent new instance
	private ConfigPropertyParser() {}
	
	/**
	 * map properties object to a object
	 * 
	 * @param wrapper ClassWraper object
	 * @param properties properties object
	 * @return an object
	 */
	public static Object assignValue(ClassWrapper wrapper, Properties properties) {
	    Object object = wrapper.newInstance();
        for(SetterMethodCover method : wrapper.getMethods()) {
            Object value = properties.get(method.getKey());
            method.invoke(object, getValue(method, value));
        }
        return object;
    }
	
	/**
	 * map properties object to an object
	 * 
	 * @param wrapper ClassWrapper object
	 * @param object object to map
	 * @param properties properties object
	 */
	public static void assignValue(ClassWrapper wrapper, Object object, Properties properties) {
	    for(SetterMethodCover method : wrapper.getMethods()) {
	        Object value = properties.get(method.getKey());
	        method.invoke(object, getValue(method, value));
	    }
	}
	
	/**
	 * Transform property's value as string to value of method
	 * 
	 * @param method SetterMethodCover object
	 * @param value value to transform
	 * @return value after transform
	 */
	protected static Object getValue(SetterMethodCover method, Object value) {
	    if(method.isBoolean())
            value = Boolean.valueOf(value.toString());
        else if(method.isByte())
            value = Byte.valueOf(value.toString());
        else if(method.isChar())
            value = stringToChar(value.toString());
        else if(method.isDouble())
            value = Double.valueOf(value.toString());
        else if(method.isFloat())
            value = Float.valueOf(value.toString());
        else if(method.isInt())
            value = Integer.valueOf(value.toString());
        else if(method.isLong())
            value = Long.valueOf(value.toString());
        else if(method.isShort())
            value = Short.valueOf(value.toString());
        else if(method.isString())
            value = value.toString();
	    return value;
	}
	
}
