package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.EzyTryCatch;
import com.tvd12.ezyfox.core.util.EzyTryCatchAnnotations;

public class EzyTryCatchAnnotationsTest {

	@Test
	public void test() throws Exception {
		assert EzyTryCatchAnnotations.getExceptionClasses(
				A.class.getDeclaredMethod("handleException", Exception.class)
				.getAnnotation(EzyTryCatch.class)).length == 1;
	}
	
	public static class A {
		@EzyTryCatch(IllegalArgumentException.class)
		public void handleException(Exception e) {
			
		}
	}
	
}
