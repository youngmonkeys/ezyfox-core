package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyServerEventHandler;
import com.tvd12.ezyfox.core.util.EzyServerEventHandlerAnnotations;
import com.tvd12.test.base.BaseTest;

public class EzyServerEventHandlerAnnotationsTest extends BaseTest {

	@Override
	public Class<?> getTestClass() {
		return EzyServerEventHandlerAnnotations.class;
	}
	
	@Test
	public void test() {
		assert EzyServerEventHandlerAnnotations.getEvent(
				A.class.getAnnotation(EzyServerEventHandler.class)
			).equals("hello");
		assert EzyServerEventHandlerAnnotations.getEvent(
				B.class.getAnnotation(EzyServerEventHandler.class)
			).equals("world");
	}
	
	@EzyServerEventHandler("hello")
	public static class A {
		
	}
	
	@EzyServerEventHandler(event = "world")
	public static class B {
		
	}
	
}
