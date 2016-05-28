package com.tvd12.ezyfox.core.testing.reflectutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.reflect.ReflectTypeUtil;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import static com.tvd12.ezyfox.core.reflect.ReflectTypeUtil.*;
import static org.testng.Assert.*;

public class ReflectTypeUtilTest extends BaseTest {
	
	public static final Logger LOGGER
			= LoggerFactory.getLogger(ReflectTypeUtilTest.class);
	
	@Test
	public void testIsObjectPerformance() {
		long time = Performance.create()
				.test(new Script() {
                    public void execute() {ReflectTypeUtil.isObject(Object.class);}
                }).getTime();
		LOGGER.info("testIsObjectPerformance time " + time);
	}
	
	@Override
	public Class<?> getTestClass() {
	    return ReflectTypeUtil.class;
	}
	
	@Test
	public void testTrueCase() {
	    assertTrue(isEnum(ABC.class));
	    
	    assertTrue(isPrimitive(Integer.TYPE));
	    
	    assertTrue(isPrimitiveBool(Boolean.TYPE));
	    assertTrue(isPrimitiveByte(Byte.TYPE));
	    assertTrue(isPrimitiveChar(Character.TYPE));
	    assertTrue(isPrimitiveDouble(Double.TYPE));
	    assertTrue(isPrimitiveFloat(Float.TYPE));
	    assertTrue(isPrimitiveInt(Integer.TYPE));
	    assertTrue(isPrimitiveLong(Long.TYPE));
	    assertTrue(isPrimitiveShort(Short.TYPE));
	    assertTrue(isString(String.class));
	    
	    assertTrue(isPrimitiveBoolArray(boolean[].class));
	    assertTrue(isPrimitiveByteArray(byte[].class));
	    assertTrue(isPrimitiveCharArray(char[].class));
	    assertTrue(isPrimitiveDoubleArray(double[].class));
	    assertTrue(isPrimitiveFloatArray(float[].class));
	    assertTrue(isPrimitiveIntArray(int[].class));
	    assertTrue(isPrimitiveLongArray(long[].class));
	    assertTrue(isPrimitiveShortArray(short[].class));
	    assertTrue(isStringArray(String[].class));
	    
	    assertTrue(isWrapperBool(Boolean.class));
	    assertTrue(isWrapperByte(Byte.class));
	    assertTrue(isWrapperChar(Character.class));
	    assertTrue(isWrapperDouble(Double.class));
	    assertTrue(isWrapperFloat(Float.class));
	    assertTrue(isWrapperInt(Integer.class));
	    assertTrue(isWrapperLong(Long.class));
	    assertTrue(isWrapperShort(Short.class));
	    assertTrue(isString(String.class));
	    
	    assertTrue(isWrapperBoolArray(Boolean[].class));
	    assertTrue(isWrapperByteArray(Byte[].class));
	    assertTrue(isWrapperCharArray(Character[].class));
	    assertTrue(isWrapperDoubleArray(Double[].class));
	    assertTrue(isWrapperFloatArray(Float[].class));
	    assertTrue(isWrapperIntArray(Integer[].class));
	    assertTrue(isWrapperLongArray(Long[].class));
	    assertTrue(isWrapperShortArray(Short[].class));
	    assertTrue(isStringArray(String[].class));
	    
	    assertTrue(isBoolArray(Boolean[].class));
        assertTrue(isByteArray(Byte[].class));
        assertTrue(isCharArray(Character[].class));
        assertTrue(isDoubleArray(Double[].class));
        assertTrue(isFloatArray(Float[].class));
        assertTrue(isIntArray(Integer[].class));
        assertTrue(isLongArray(Long[].class));
        assertTrue(isShortArray(Short[].class));
        assertTrue(isStringArray(String[].class));
        assertTrue(isObjectArray(Object[].class));
	}
	
	@Test
	public void testFalseCase() {
	    assertFalse(isEnum(String.class));
        
	    assertFalse(isPrimitive(Integer.class));
        
	    assertFalse(isPrimitiveBool(Boolean.class));
	    assertFalse(isPrimitiveByte(Byte.class));
	    assertFalse(isPrimitiveChar(Character.class));
	    assertFalse(isPrimitiveDouble(Double.class));
	    assertFalse(isPrimitiveFloat(Float.class));
	    assertFalse(isPrimitiveInt(Integer.class));
	    assertFalse(isPrimitiveLong(Long.class));
	    assertFalse(isPrimitiveShort(Short.class));
	    assertFalse(isString(Short.class));
	    
	    assertFalse(isPrimitiveBoolArray(boolean.class));
        assertFalse(isPrimitiveByteArray(byte.class));
        assertFalse(isPrimitiveCharArray(char.class));
        assertFalse(isPrimitiveDoubleArray(double.class));
        assertFalse(isPrimitiveFloatArray(float.class));
        assertFalse(isPrimitiveIntArray(int.class));
        assertFalse(isPrimitiveLongArray(long.class));
        assertFalse(isPrimitiveShortArray(short.class));
        assertFalse(isStringArray(String.class));
        
	    assertFalse(isPrimitiveBoolArray(Boolean[].class));
	    assertFalse(isPrimitiveByteArray(Byte[].class));
	    assertFalse(isPrimitiveCharArray(Character[].class));
	    assertFalse(isPrimitiveDoubleArray(Double[].class));
	    assertFalse(isPrimitiveFloatArray(Float[].class));
	    assertFalse(isPrimitiveIntArray(Integer[].class));
	    assertFalse(isPrimitiveLongArray(Long[].class));
	    assertFalse(isPrimitiveShortArray(Short[].class));
	    assertFalse(isStringArray(Short[].class));
        
	    assertFalse(isWrapperBool(boolean.class));
	    assertFalse(isWrapperByte(byte.class));
	    assertFalse(isWrapperChar(char.class));
	    assertFalse(isWrapperDouble(double.class));
	    assertFalse(isWrapperFloat(float.class));
	    assertFalse(isWrapperInt(int.class));
	    assertFalse(isWrapperLong(long.class));
        assertFalse(isWrapperShort(short.class));
        assertFalse(isString(Short.class));
        
        assertFalse(isWrapperBoolArray(Boolean.class));
        assertFalse(isWrapperByteArray(Byte.class));
        assertFalse(isWrapperCharArray(Character.class));
        assertFalse(isWrapperDoubleArray(Double.class));
        assertFalse(isWrapperFloatArray(Float.class));
        assertFalse(isWrapperIntArray(Integer.class));
        assertFalse(isWrapperLongArray(Long.class));
        assertFalse(isWrapperShortArray(Short.class));
        assertFalse(isStringArray(String.class));
        
        assertFalse(isWrapperBoolArray(boolean[].class));
        assertFalse(isWrapperByteArray(byte[].class));
        assertFalse(isWrapperCharArray(char[].class));
        assertFalse(isWrapperDoubleArray(double[].class));
        assertFalse(isWrapperFloatArray(float[].class));
        assertFalse(isWrapperIntArray(int[].class));
        assertFalse(isWrapperLongArray(long[].class));
        assertFalse(isWrapperShortArray(short[].class));
        assertFalse(isStringArray(Short[].class));
        
        assertFalse(isBoolArray(Boolean.class));
        assertFalse(isByteArray(Byte.class));
        assertFalse(isCharArray(Character.class));
        assertFalse(isDoubleArray(Double.class));
        assertFalse(isFloatArray(Float.class));
        assertFalse(isIntArray(Integer.class));
        assertFalse(isLongArray(Long.class));
        assertFalse(isShortArray(Short.class));
        assertFalse(isStringArray(String.class));
        
        assertFalse(isBoolArray(Short[].class));
        assertFalse(isByteArray(Short[].class));
        assertFalse(isCharArray(Short[].class));
        assertFalse(isDoubleArray(Short[].class));
        assertFalse(isFloatArray(Short[].class));
        assertFalse(isIntArray(Short[].class));
        assertFalse(isLongArray(Short[].class));
        assertFalse(isShortArray(String[].class));
        assertFalse(isStringArray(Short[].class));
        assertFalse(isObjectArray(Object.class));
        assertFalse(isObjectArray(String[].class));
	}
	
	public static enum ABC {
	  A, B, C  
	};
	
}
