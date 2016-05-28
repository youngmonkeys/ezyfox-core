package com.tvd12.ezyfox.core.testing.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.RequestParamMethod;

import lombok.Data;

import static org.testng.Assert.*;

public class RequestParamMethodTest {
	
	private static final Logger LOGGER
			= LoggerFactory.getLogger(RequestParamMethodTest.class);
	
	@Test
	public void testValidCase() throws ExtensionException {
		Field field = ReflectFieldUtil.getField("name", ClassA.class);
		Method method1 = ReflectMethodUtil.getMethod("setAddresses", ClassA.class, List.class);
		Method method2 = ReflectMethodUtil.getMethod("setHello", ClassA.class, ClassA[].class);
		
		RequestParamMethod paramMethod1 = new RequestParamMethod(ClassA.class, field);
		RequestParamMethod paramMethod2 = new RequestParamMethod(ClassA.class, method1);
		RequestParamMethod paramMethod3 = new RequestParamMethod(ClassA.class, method2);
		
		assertEquals(null, paramMethod1.getComponentType());
		assertEquals(null, paramMethod1.getGenericType());
		assertEquals("name", paramMethod1.getKey());
		assertEquals("setName", paramMethod1.getMethod().getName());
		
		assertEquals(null, paramMethod2.getComponentType());
		assertEquals(String.class, paramMethod2.getGenericType());
		assertEquals("adrs", paramMethod2.getKey());
		assertEquals("setAddresses", paramMethod2.getMethod().getName());
		
		assertEquals(ClassA.class, paramMethod3.getComponentType());
		assertEquals(null, paramMethod3.getGenericType());
		assertEquals("hello", paramMethod3.getKey());
		assertEquals("setHello", paramMethod3.getMethod().getName());
		
	}
	
	@Test(expectedExceptions = {IllegalStateException.class})
	public void testInvalidCase1() throws ExtensionException {
		Method method = ReflectMethodUtil.getMethod("setA", ClassA.class);
		new RequestParamMethod(ClassA.class, method);
	}
	
	@Test(expectedExceptions = {IllegalStateException.class})
	public void testInvalidCase2() throws ExtensionException {
		Method method =
		ReflectMethodUtil.getMethod("setB", ClassA.class, Integer.class, Integer.class);
		new RequestParamMethod(ClassA.class, method);
	}
	
	@Data
	public static class ClassA {
		
		@RequestParam
		private String name;
		
		@RequestParam("adrs")
		public void setAddresses(List<String> addresses) {
			LOGGER.info("ClassA#address = " + addresses);
		}
		
		@RequestParam
		public void setHello(ClassA[] classA) {
			
		}
		
		@RequestParam
		public void setA() {
			
		}
		
		@RequestParam
		public void setB(Integer a, Integer b) {
			
		}
		
	}
	
}
