package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestInterceptor;
import com.tvd12.ezyfox.core.util.EzyClientRequestInterceptorAnnotations;
import com.tvd12.test.base.BaseTest;

public class EzyClientRequestInterceptorAnnotationsTest extends BaseTest {

	@Override
	public Class<?> getTestClass() {
		return EzyClientRequestInterceptorAnnotations.class;
	}
	
	@Test
	public void test() {
		assert EzyClientRequestInterceptorAnnotations.getPriority(InteceptorA.class) == 0;
		assert EzyClientRequestInterceptorAnnotations.getPriority(InteceptorB.class) == 1;
	}
	
	public static class InteceptorA {}
	
	@EzyClientRequestInterceptor(priority = 1)
	public static class InteceptorB {}
	
}
