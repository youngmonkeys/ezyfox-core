package com.tvd12.ezyfox.core.structure;

import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.isCollection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectTypeUtil;

import lombok.Getter;

/**
 * Because sometimes we need serialize or deserialize an object, but using java reflection 
 * is too slow, so we need use reflection in first time to load structure of the object
 * And this class support to hold structure of a method in a object's class
 * 
 * @author tavandung12
 *
 */

@Getter
public class MethodCover {

    // return type or parameter type of method
	protected Class<?> type;

	// generic type of return type or parameter type of method
	protected Class<?> genericType;
	
	// java method to parse structure
	protected Method method;
	
	// java field to get setter or getter method
	protected Field field;
	
	// key associated to method
	protected String key;
	
	// sometimes we need shared method's data to someone and sometimes we don't
	protected boolean isHidden = false;
	
	// return type or parameter type is a primitive type
	protected boolean isPrimitive;
	
	// return type or parameter type is primitive byte
	protected boolean isPrimitiveByte;
	
	// return type or parameter type is primitive boolean
	protected boolean isPrimitiveBoolean;
	
	// return type or parameter type is primitive char
	protected boolean isPrimitiveChar;
	
	// return type or parameter type is primitive double
	protected boolean isPrimitiveDouble;
	
	// return type or parameter type is primitive float
	protected boolean isPrimitiveFloat;
	
	// return type or parameter type is primitive int
	protected boolean isPrimitiveInt;
	
	// return type or parameter type is primitive long
	protected boolean isPrimitiveLong;
	
	// return type or parameter type is primitive short
	protected boolean isPrimitiveShort;
	
	// return type or parameter type is wrapper Byte
	protected boolean isWrapperByte;
	
	// return type or parameter type is wrapper Boolean
	protected boolean isWrapperBoolean;
	
	// return type or parameter type is wrapper Character
	protected boolean isWrapperChar;
	
	// return type or parameter type is wrapper Double
	protected boolean isWrapperDouble;
	
	// return type or parameter type is wrapper Float
	protected boolean isWrapperFloat;
	
	// return type or parameter type is wrapper Integer
	protected boolean isWrapperInt;
	
	// return type or parameter type is wrapper Long
	protected boolean isWrapperLong;
	
	// return type or parameter type is wrapper Short
	protected boolean isWrapperShort;
	
	// return type or parameter type is String
	protected boolean isString;
	
	// return type or parameter type is Object
	protected boolean isObject;
	
	// return type or parameter type is Collection
	protected boolean isColection;
	
	// return type or parameter type is array
	protected boolean isArray;
	
	// return type or parameter type is array of objects
	protected boolean isObjectArray;
	
	// return type or parameter type is two-dimensions array
	protected boolean isTwoDimensionsArray;
	
	// return type or parameter type is two-dimensions object array
	protected boolean isTwoDimensionsObjectArray;
	
	// return type or parameter type is collection of objects
	protected boolean isObjectCollection;
	
	// return type or parameter type is collection of arrays
	protected boolean isArrayCollection;
	
	// return type or parameter type is collection of object's array
	protected boolean isArrayObjectCollection;
	
	// return type or parameter type is collection of Byte
	protected boolean isByteCollection;
	
	/**
	 * parse java method
	 * 
	 * @param clazz declared class of method
	 * @param meth java method to parse
	 */
	protected void initWithMethod(Class<?> clazz, Method meth) {
		this.method = meth;
		this.key = getKey(method);
		this.type = getTypeFromMethod(method);
		this.checkSingleType();
		this.genericType = (isColection()) 
		        ? getGenericTypeFromMethod(method) : null;
		this.checkArrayType();
	}
	
	/**
	 * parse java field
	 * 
	 * @param clazz declared class of field
	 * @param field field to parse
	 */
	protected void initWithField(Class<?> clazz, Field field) {
	    this.field = field;
		this.type = field.getType();
		this.checkSingleType();
		this.key = getKey(field);
		this.genericType = getGenericTypeFromField(field);
		this.checkArrayType();
	}
	
	/**
	 * Get generic type of return type or parameter type of method
	 * 
	 * @param method method to get type
	 * @return generic type
	 */
	protected Class<?> getGenericTypeFromMethod(Method method) {return null;}
	
	/**
	 * Get return type or parameter type of method
	 * 
	 * @param method java method to get type
	 * @return return type or parameter type of method 
	 */
	protected Class<?> getTypeFromMethod(Method method) {
	    Class<?> type = method.getReturnType();
	    return (type != Void.TYPE) ? type :
	           (method.getParameterTypes().length == 1) 
	                ? method.getParameterTypes()[0] : null;
	}
	
	/**
	 * Get generic type from field
	 * 
	 * @param field java field
	 * @return generic type
	 */
	protected Class<?> getGenericTypeFromField(Field field) {
		if(!isColection()) return null;
		try {
			return ReflectFieldUtil.getGenericType(field);
		} catch (ExtensionException e) {
			throw new IllegalStateException(e);
		}
	}
	
	/**
	 * Get component type of array (array is type or return type or parameter type of java method)
	 * 
	 * @return component type
	 */
	public Class<?> getComponentType() {
		return type.getComponentType();
	}
	
	/**
	 * Get java method's name
	 * 
	 * @return java method's name
	 */
	public String getMethodName() {
	    return method.getName();
	}
	
	/**
	 * If method be annotated with an annotation, read that annotation and get a key.
	 * If method not annotated wit any annotations, then parse method name to get a key
	 * 
	 * @param method method to parse
	 * @return a key name
	 */
	protected String getKey(Method method) {
		String mname = method.getName();
		if(mname.startsWith("get")
				|| mname.startsWith("set")
				|| mname.startsWith("has"))
			mname = mname.substring(3);
		if(mname.startsWith("is"))
			mname = mname.substring(2);
		return WordUtils.uncapitalize(mname);
	}
	
	/**
	 * If field be annotated with an annotation, read that annotation and get a key.
     * If field not annotated wit any annotations, then parse field name to get a key
	 * 
	 * @param field
	 * @return
	 */
	protected String getKey(Field field) {
		return field.getName();
	}

	/**
	 * Check return or parameter type of method and save to boolean variables
	 */
	private void checkSingleType() {
		isPrimitiveByte = (type == Byte.TYPE);
		isPrimitiveBoolean = (type == Boolean.TYPE);
		isPrimitiveChar = (type == Character.TYPE);
		isPrimitiveDouble = (type == Double.TYPE);
		isPrimitiveFloat = (type == Float.TYPE);
		isPrimitiveInt = (type == Integer.TYPE);
		isPrimitiveLong = (type == Long.TYPE);
		isPrimitiveShort = (type == Short.TYPE);
		isWrapperByte = (type == Byte.class);
		isWrapperBoolean = (type == Boolean.class);
		isWrapperChar = (type == Character.class);
		isWrapperDouble = (type == Double.class);
		isWrapperFloat = (type == Float.class);
		isWrapperInt = (type == Integer.class);
		isWrapperLong = (type == Long.class);
		isWrapperShort = (type == Short.class);
		isString = (type == String.class);
		isObject = ReflectTypeUtil.isObject(type);
		isColection = isCollection(type);
		isArray = type.isArray();
		isPrimitive = ReflectTypeUtil.isPrimitive(type);
	}
	
	/**
	 * If return type of parameter type of method is array, then determine their component type
	 */
	private void checkArrayType() {
        checkObjectArray();
        checkByteCollection();
        checkArrayCollection();
        checkObjectCollection();
        checkArrayObjectCollection();
        checkTwoDimensionsArray();
        checkTwoDimensionsObjectArray();
        checkTwoDimensionsArrayCollection();
    }
	
	/**
	 * Check whether return type or parameter type is collection
	 */
	private void checkByteCollection() {
        isByteCollection = isColection 
                && ReflectTypeUtil.isWrapperByte(genericType);
    }
    
	/**
	 * Check whether return type or parameter type is collection of objects
	 */
    private void checkObjectCollection() {
        isObjectCollection = isColection 
                && ReflectTypeUtil.isObject(genericType);
    }
    
    /**
     * Check whether return type of parameter type is collection of arrays
     */
    private void checkArrayCollection() {
        isArrayCollection = isColection
                && genericType.isArray();
    }
    
    /**
     * Check whether return type or parameter type is collection of object's array
     */
    private void checkArrayObjectCollection() {
        isArrayObjectCollection = isArrayCollection
                && ReflectTypeUtil.isObject(genericType.getComponentType());
    }
    
    /**
     * Check whether return type or parameter type is array of objects
     */
    private void checkObjectArray() {
        isObjectArray = isArray 
                && ReflectTypeUtil.isObject(getComponentType());
    }
    
    /**
     * Check whether return type or parameter type is two-dimensions array
     * 
     * @throws IllegalStateException when type is two-dimensions array
     */
    protected void checkTwoDimensionsArray() {
        isTwoDimensionsArray = isArray && ReflectTypeUtil.isArray(getComponentType());
    }
    
    /**
     * Check whether return type or parameter type is two-dimensions object array
     * 
     * @throws IllegalStateException when type is two-dimensions array
     */
    protected void checkTwoDimensionsObjectArray() {
        isTwoDimensionsObjectArray = isTwoDimensionsArray && 
                ReflectTypeUtil.isObjectArray(getComponentType());
    }
    
    /**
     * Check whether return type or parameter type is collection of two-dimension arrays
     * 
     * @throws IllegalStateException when type is collection of two-dimension arrays
     */
    private void checkTwoDimensionsArrayCollection() {
        if(isArrayCollection && ReflectTypeUtil
                .isArray(getGenericType().getComponentType()))
            throw new IllegalStateException("Unsupport collection of two-dimensions array "
                    + "(on field/method " 
                    + ((method != null) ? getMethodName() : field.getName()) + ")");
    }
    
    //
	/**
	 * Check whether return type or parameter type is wrapper Byte or primitive byte
	 * 
	 * @return true or false
	 */
	public boolean isByte() {
        return isWrapperByte || isPrimitiveByte;
    }

	/**
     * Check whether return type or parameter type is wrapper Boolean or primitive boolean
     * 
     * @return true or false
     */
    public boolean isBoolean() {
        return isWrapperBoolean || isPrimitiveBoolean;
    }

    /**
     * Check whether return type or parameter type is wrapper Character or primitive char
     * 
     * @return true or false
     */
    public boolean isChar() {
        return isPrimitiveChar || isWrapperChar;
    }

    /**
     * Check whether return type or parameter type is wrapper Double or primitive double
     * 
     * @return true or false
     */
    public boolean isDouble() {
        return isPrimitiveDouble || isWrapperDouble;
    }

    /**
     * Check whether return type or parameter type is wrapper Float or primitive float
     * 
     * @return true or false
     */
    public boolean isFloat() {
        return isPrimitiveFloat || isWrapperFloat;
    }

    /**
     * Check whether return type or parameter type is wrapper Integer or primitive int
     * 
     * @return true or false
     */
    public boolean isInt() {
        return isPrimitiveInt || isWrapperInt;
    }

    /**
     * Check whether return type or parameter type is wrapper Long or primitive long
     * 
     * @return true or false
     */
    public boolean isLong() {
        return isPrimitiveLong || isWrapperLong;
    }

    /**
     * Check whether return type or parameter type is wrapper Short or primitive short
     * 
     * @return true or false
     */
    public boolean isShort() {
        return isPrimitiveShort || isWrapperShort;
    }
    
    //
    /**
     * Check whether return type or parameter type is primitive byte array
     * 
     * @return true or false
     */
	public boolean isPrimitiveByteArray() {
		return isArray && getComponentType() == Byte.TYPE;
	}

	/**
     * Check whether return type or parameter type is primitive boolean array
     * 
     * @return true or false
     */
	public boolean isPrimitiveBooleanArray() {
		return isArray && getComponentType() == Boolean.TYPE;
	}

	/**
     * Check whether return type or parameter type is primitive char array
     * 
     * @return true or false
     */
	public boolean isPrimitiveCharArray() {
		return isArray && getComponentType() == Character.TYPE;
	}

	/**
     * Check whether return type or parameter type is primitive double array
     * 
     * @return true or false
     */
	public boolean isPrimitiveDoubleArray() {
		return isArray && getComponentType() == Double.TYPE;
	}
	
	/**
     * Check whether return type or parameter type is primitive float array
     * 
     * @return true or false
     */
	public boolean isPrimitiveFloatArray() {
		return isArray && getComponentType() == Float.TYPE;
	}

	/**
     * Check whether return type or parameter type is primitive int array
     * 
     * @return true or false
     */
	public boolean isPrimitiveIntArray() {
		return isArray && getComponentType() == Integer.TYPE;
	}

	/**
     * Check whether return type or parameter type is primitive long array
     * 
     * @return true or false
     */
	public boolean isPrimitiveLongArray() {
		return isArray && getComponentType() == Long.TYPE;
	}

	/**
     * Check whether return type or parameter type is primitive short array
     * 
     * @return true or false
     */
	public boolean isPrimitiveShortArray() {
		return isArray && getComponentType() == Short.TYPE;
	}

	//===== wrapper array =====
	/**
     * Check whether return type or parameter type is wrapper Byte array
     * 
     * @return true or false
     */
	public boolean isWrapperByteArray() {
		return isArray && getComponentType() == Byte.class;
	}

	/**
     * Check whether return type or parameter type is wrapper Boolean array
     * 
     * @return true or false
     */
	public boolean isWrapperBooleanArray() {
		return isArray && getComponentType() == Boolean.class;
	}

	/**
     * Check whether return type or parameter type is wrapper Char array
     * 
     * @return true or false
     */
	public boolean isWrapperCharArray() {
		return isArray && getComponentType() == Character.class;
	}

	/**
     * Check whether return type or parameter type is wrapper Double array
     * 
     * @return true or false
     */
	public boolean isWrapperDoubleArray() {
		return isArray && getComponentType() == Double.class;
	}

	/**
     * Check whether return type or parameter type is wrapper Float array
     * 
     * @return true or false
     */
	public boolean isWrapperFloatArray() {
		return isArray && getComponentType() == Float.class;
	}

	/**
     * Check whether return type or parameter type is wrapper Integer array
     * 
     * @return true or false
     */
	public boolean isWrapperIntArray() {
		return isArray && getComponentType() == Integer.class;
	}

	/**
     * Check whether return type or parameter type is wrapper Long array
     * 
     * @return true or false
     */
	public boolean isWrapperLongArray() {
		return isArray && getComponentType() == Long.class;
	}
	
	/**
     * Check whether return type or parameter type is wrapper Short array
     * 
     * @return true or false
     */
	public boolean isWrapperShortArray() {
        return isArray && getComponentType() == Short.class;
    }
	

	//===== array =====
	/**
     * Check whether return type or parameter type is wrapper Byte or primitive byte array
     * 
     * @return true or false
     */
	public boolean isByteArray() {
        return isArray && ReflectTypeUtil.isByte(getComponentType());
    }

	/**
     * Check whether return type or parameter type is wrapper Boolean or primitive boolean array
     * 
     * @return true or false
     */
    public boolean isBooleanArray() {
        return isArray && ReflectTypeUtil.isBool(getComponentType());
    }

    /**
     * Check whether return type or parameter type is wrapper Character or primitive char array
     * 
     * @return true or false
     */
    public boolean isCharArray() {
        return isArray && ReflectTypeUtil.isChar(getComponentType());
    }

    /**
     * Check whether return type or parameter type is wrapper Double or primitive double array
     * 
     * @return true or false
     */
    public boolean isDoubleArray() {
        return isArray && ReflectTypeUtil.isDouble(getComponentType());
    }

    /**
     * Check whether return type or parameter type is wrapper Float or primitive float array
     * 
     * @return true or false
     */
    public boolean isFloatArray() {
        return isArray && ReflectTypeUtil.isFloat(getComponentType());
    }

    /**
     * Check whether return type or parameter type is wrapper Integer or primitive int array
     * 
     * @return true or false
     */
    public boolean isIntArray() {
        return isArray && ReflectTypeUtil.isInt(getComponentType());
    }

    /**
     * Check whether return type or parameter type is wrapper Long or primitive long array
     * 
     * @return true or false
     */
    public boolean isLongArray() {
        return isArray && ReflectTypeUtil.isLong(getComponentType());
    }

    /**
     * Check whether return type or parameter type is wrapper Short or primitive short array
     * 
     * @return true or false
     */
	public boolean isShortArray() {
		return isArray && ReflectTypeUtil.isShort(getComponentType());
	}
	
	/**
     * Check whether return type or parameter type is String array
     * 
     * @return true or false
     */
	public boolean isStringArray() {
	    return isArray && ReflectTypeUtil.isString(getComponentType());
	}
	
	//===== collection =====
	/**
     * Check whether return type or parameter type is wrapper Byte collection
     * 
     * @return true or false
     */
    public boolean isByteCollection() {
        return isColection && ReflectTypeUtil.isByte(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Boolean collection
     * 
     * @return true or false
     */
    public boolean isBooleanCollection() {
        return isColection && ReflectTypeUtil.isBool(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Character collection
     * 
     * @return true or false
     */
    public boolean isCharCollection() {
        return isColection && ReflectTypeUtil.isChar(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Double collection
     * 
     * @return true or false
     */
    public boolean isDoubleCollection() {
        return isColection && ReflectTypeUtil.isDouble(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Float collection
     * 
     * @return true or false
     */
    public boolean isFloatCollection() {
        return isColection && ReflectTypeUtil.isFloat(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Integer collection
     * 
     * @return true or false
     */
    public boolean isIntCollection() {
        return isColection && ReflectTypeUtil.isInt(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Long collection
     * 
     * @return true or false
     */
    public boolean isLongCollection() {
        return isColection && ReflectTypeUtil.isLong(getGenericType());
    }

    /**
     * Check whether return type or parameter type is wrapper Short collection
     * 
     * @return true or false
     */
    public boolean isShortCollection() {
        return isColection && ReflectTypeUtil.isShort(getGenericType());
    }
    
    /**
     * Check whether return type or parameter type is String collection
     * 
     * @return true or false
     */
    public boolean isStringCollection() {
        return isColection && ReflectTypeUtil.isString(getGenericType());
    }
	
}
