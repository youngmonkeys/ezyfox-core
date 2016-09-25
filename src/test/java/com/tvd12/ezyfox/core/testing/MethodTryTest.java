package com.tvd12.ezyfox.core.testing;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;

public class MethodTryTest {

	@Test
	public void testGetGenericType() throws ExtensionException, ExtensionException {
		Method setterMethod = ReflectMethodUtil.getMethod("setList", ClassA.class, List.class);
		Method getterMethod = ReflectMethodUtil.getMethod("getList", ClassA.class);
		assertEquals(ClassB.class, ReflectMethodUtil.getParameterGenericType(setterMethod));
		assertEquals(ClassB.class, ReflectMethodUtil.getReturnGenericType(getterMethod));
	}
	
	public static void main(String[] args) throws ExtensionException, ExtensionException {
		new MethodTryTest().testGetGenericType();
	}
	
	public static class ClassA {
		public List<ClassB> list;
		
		public void setList(List<ClassB> list) {
			this.list = list;
		}
		
		public List<ClassB> getList() {
			return this.list;
		}
	}
	
	public static class ClassB {
		
	}
	
}
