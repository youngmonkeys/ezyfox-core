package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestListener;
import com.tvd12.ezyfox.core.util.EzyClientRequestListenerAnnotations;
import com.tvd12.test.base.BaseTest;

public class EzyClientRequestListenerAnnotationsTest extends BaseTest {

	@Override
	public Class<?> getTestClass() {
		return EzyClientRequestListenerAnnotations.class;
	}
	
	@Test
	public void test() {
		assert EzyClientRequestListenerAnnotations.getCommand(
				A.class.getAnnotation(EzyClientRequestListener.class)
			).equals("hello");
		assert EzyClientRequestListenerAnnotations.getCommand(
				B.class.getAnnotation(EzyClientRequestListener.class)
			).equals("world");
	}
	
	@EzyClientRequestListener("hello")
	public static class A {
		
	}
	
	@EzyClientRequestListener(command = "world")
	public static class B {
		
	}
	
}
