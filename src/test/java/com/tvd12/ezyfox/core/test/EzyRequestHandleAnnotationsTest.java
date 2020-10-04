package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyClientRequestController;
import com.tvd12.ezyfox.core.annotation.EzyRequestHandle;
import com.tvd12.ezyfox.core.util.EzyRequestHandleAnnotations;

public class EzyRequestHandleAnnotationsTest {

	@Test
	public void test() throws Exception {
		assert "a".equals(EzyRequestHandleAnnotations.getCommand(
				TestClientRequestController.class.getDeclaredMethod("handle1")
				.getAnnotation(EzyRequestHandle.class)));
		assert "b".equals(EzyRequestHandleAnnotations.getCommand(
				TestClientRequestController.class.getDeclaredMethod("handle2")
				.getAnnotation(EzyRequestHandle.class)));
	}
	
	@EzyClientRequestController
	public static class TestClientRequestController {
		
		@EzyRequestHandle("a")
		public void handle1() {
		}
		
		@EzyRequestHandle(command = "b")
		public void handle2() {
		}
		
	}
	
}
