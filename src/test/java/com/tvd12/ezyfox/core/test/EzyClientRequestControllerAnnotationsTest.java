package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestController;
import com.tvd12.ezyfox.core.util.EzyClientRequestControllerAnnotations;

public class EzyClientRequestControllerAnnotationsTest {

	@Test
	public void test() throws Exception {
		assert "a".equals(EzyClientRequestControllerAnnotations
				.getGroup(TestClientRequestController1.class));
		assert "b".equals(EzyClientRequestControllerAnnotations
				.getGroup(TestClientRequestController2.class));
	}
	
	@EzyClientRequestController("a")
	public static class TestClientRequestController1 {
	}
	
	@EzyClientRequestController(value = " ", group = "b")
	public static class TestClientRequestController2 {
	}
	
}
