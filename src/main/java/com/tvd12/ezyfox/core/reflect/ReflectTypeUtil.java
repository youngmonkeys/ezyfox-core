package com.tvd12.ezyfox.core.reflect;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utilities for working with {@link Type}s by reflection
 * 
 * @author tavandung12
 *
 */

public final class ReflectTypeUtil {

    // prevent new instance
	private ReflectTypeUtil() {}
	
	/**
	 * check whether type is map 
	 * 
	 * @param type type to check
	 * @return true or false
	 */
	public static boolean isMap(Class<?> type) {
	    return Map.class.isAssignableFrom(type);
	}
	
	/**
     * Check whether type is primitive boolean 
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveBool(Class<?> type) {
        return type == Boolean.TYPE;
    }
	
	/**
	 * Check whether type is primitive byte 
	 * 
	 * @param type type to check
	 * @return true or false
	 */
	public static boolean isPrimitiveByte(Class<?> type) {
        return type == Byte.TYPE;
    }
	
	/**
     * Check whether type is primitive char 
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveChar(Class<?> type) {
        return type == Character.TYPE;
    }
	
	/**
     * Check whether type is primitive double 
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveDouble(Class<?> type) {
        return type == Double.TYPE;
    }
	
	/**
     * Check whether type is primitive float 
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveFloat(Class<?> type) {
        return type == Float.TYPE;
    }
	
	/**
     * Check whether type is primitive int 
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveInt(Class<?> type) {
        return type == Integer.TYPE;
    }
	
	/**
     * Check whether type is primitive long 
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveLong(Class<?> type) {
        return type == Long.TYPE;
    }
	
	/**
     * Check whether type is primitive short 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveShort(Class<?> type) {
        return type == Short.TYPE;
    }
    
    //
    /**
     * Check whether type is wrapper Boolean 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperBool(Class<?> type) {
        return type == Boolean.class;
    }
    
    /**
     * Check whether type is wrapper Byte 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperByte(Class<?> type) {
        return type == Byte.class;
    }
    
    /**
     * Check whether type is wrapper Character 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperChar(Class<?> type) {
        return type == Character.class;
    }
    
    /**
     * Check whether type is wrapper Double 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperDouble(Class<?> type) {
        return type == Double.class;
    }
    
    /**
     * Check whether type is wrapper Float 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperFloat(Class<?> type) {
        return type == Float.class;
    }
    
    /**
     * Check whether type is wrapper Integer 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperInt(Class<?> type) {
        return type == Integer.class;
    }
    
    /**
     * Check whether type is wrapper Long 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperLong(Class<?> type) {
        return type == Long.class;
    }
    
    /**
     * Check whether type is wrapper Short 
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperShort(Class<?> type) {
        return type == Short.class;
    }
    
    //
    /**
     * Check whether type is primitive boolean or wrapper Boolean
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isBool(Class<?> type) {
        return type == Boolean.TYPE || type == Boolean.class;
    }
    
    /**
     * Check whether type is primitive byte or wrapper Byte
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isByte(Class<?> type) {
		return type == Byte.TYPE || type == Byte.class;
	}
	
	/**
     * Check whether type is primitive char or wrapper Character
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isChar(Class<?> type) {
        return type == Character.TYPE || type == Character.class;
    }
	
	/**
     * Check whether type is primitive float or wrapper Float
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isFloat(Class<?> type) {
        return type == Float.TYPE || type == Float.class;
    }
	
	/**
     * Check whether type is primitive double or wrapper Double
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isDouble(Class<?> type) {
        return type == Double.TYPE || type == Double.class;
    }
    
    /**
     * Check whether type is primitive int or wrapper Integer
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isInt(Class<?> type) {
		return type == Integer.TYPE || type == Integer.class;
	}
	
	/**
     * Check whether type is primitive long or wrapper Long
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isLong(Class<?> type) {
		return type == Long.TYPE || type == Long.class;
	}
	
	/**
     * Check whether type is primitive short or wrapper Short
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isShort(Class<?> type) {
        return type == Short.TYPE || type == Short.class;
    }
	
	//
	/**
     * Check whether type is String
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isString(Class<?> type) {
        return type == String.class;
    }
	
	//
	/**
     * Check whether type is array
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isArray(Class<?> type) {
        return type.isArray();
    }
	
	// primitive array
	/**
     * Check whether type is primitive boolean array
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveBoolArray(Class<?> type) {
        return isArray(type) && isPrimitiveBool(type.getComponentType());
    }
	
	/**
     * Check whether type is primitive byte array
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isPrimitiveByteArray(Class<?> type) {
        return isArray(type) && isPrimitiveByte(type.getComponentType());
    }
	
	/**
     * Check whether type is primitive char array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveCharArray(Class<?> type) {
        return isArray(type) && isPrimitiveChar(type.getComponentType());
    }
    
    /**
     * Check whether type is primitive float array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveFloatArray(Class<?> type) {
        return isArray(type) && isPrimitiveFloat(type.getComponentType());
    }
    
    /**
     * Check whether type is primitive double array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveDoubleArray(Class<?> type) {
        return isArray(type) && isPrimitiveDouble(type.getComponentType());
    }
    
    /**
     * Check whether type is primitive int array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveIntArray(Class<?> type) {
        return isArray(type) && isPrimitiveInt(type.getComponentType());
    }
    
    /**
     * Check whether type is primitive long array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveLongArray(Class<?> type) {
        return isArray(type) && isPrimitiveLong(type.getComponentType());
    }
    
    /**
     * Check whether type is primitive short array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitiveShortArray(Class<?> type) {
        return isArray(type) && isPrimitiveShort(type.getComponentType());
    }
	// end primitive array
    
    // wrapper array
    /**
     * Check whether type is wrapper Boolean array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperBoolArray(Class<?> type) {
        return isArray(type) && isWrapperBool(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Byte array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperByteArray(Class<?> type) {
        return isArray(type) && isWrapperByte(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Character array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperCharArray(Class<?> type) {
        return isArray(type) && isWrapperChar(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Float array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperFloatArray(Class<?> type) {
        return isArray(type) && isWrapperFloat(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Double array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperDoubleArray(Class<?> type) {
        return isArray(type) && isWrapperDouble(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Integer array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperIntArray(Class<?> type) {
        return isArray(type) && isWrapperInt(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Long array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperLongArray(Class<?> type) {
        return isArray(type) && isWrapperLong(type.getComponentType());
    }
    
    /**
     * Check whether type is wrapper Short array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isWrapperShortArray(Class<?> type) {
        return isArray(type) && isWrapperShort(type.getComponentType());
    }
    
    // end wrapper array
    /**
     * Check whether type is primitive boolean or wrapper Integer array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isBoolArray(Class<?> type) {
        return isArray(type) && isBool(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive byte or wrapper Byte array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isByteArray(Class<?> type) {
        return isArray(type) && isByte(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive char or wrapper Character array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isCharArray(Class<?> type) {
        return isArray(type) && isChar(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive double or wrapper Double array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isDoubleArray(Class<?> type) {
        return isArray(type) && isDouble(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive float or wrapper Float array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isFloatArray(Class<?> type) {
        return isArray(type) && isFloat(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive int or wrapper Integer array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isIntArray(Class<?> type) {
        return isArray(type) && isInt(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive long or wrapper Long array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isLongArray(Class<?> type) {
        return isArray(type) && isLong(type.getComponentType()) ;
    }
    
    /**
     * Check whether type is primitive short or wrapper Short array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isShortArray(Class<?> type) {
        return isArray(type) && isShort(type.getComponentType()) ;
    }
   
    //
    /**
     * Check whether type is String array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isStringArray(Class<?> type) {
        return isArray(type) && isString(type.getComponentType()) ;
    }
    //

    //
    /**
     * Check whether type is Object array
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isObjectArray(Class<?> type) {
        return isArray(type) && isObject(type.getComponentType()) ;
    }
    
    //
    /**
     * Check whether type is primitive
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive();
    }
    
    /**
     * Check whether type is enum
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isEnum(Class<?> type) {
        return type.isEnum();
    }
    
    /**
     * Check whether type is collection
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isCollection(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }
	
    /**
     * Check whether type is object
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isObject(Class<?> type) {
		return !isArray(type) 
				&& !isPrimitive(type)
				&& !isWrapperType(type)
				&& !isString(type)
				&& !isCollection(type);
	}
	
	/**
     * Check whether type is two-dimensions array or not
     * 
     * @param type type to check
     * @return true or false
     */
    public static boolean isTwoDimensionArray(Class<?> type) {
        return isArray(type) 
                && isArray(type.getComponentType());
    }
	
	 /**
     * Check whether type is wrapper
     * 
     * @param type type to check
     * @return true or false
     */
	public static boolean isWrapperType(Class<?> type) {
		return WRAPPER_TYPES.contains(type);
	}
	
    /**
     * set of wrapper type
     * 
     * @return set of wrapper type
     */
	private static Set<Class<?>> getWrapperTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		return ret;
	}
	
	// set of wrapper type
	private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
	
}
