package com.tvd12.ezyfox.core.testing.entities;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.entities.ApiLoggable;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodInvoker;

public class ApiLoggableTest extends BaseTest {

	@Test
	public void test() {
		ApiLoggable loggable = new ApiLoggable();
		MethodInvoker.create()
			.method("getLogger")
			.object(loggable)
			.invoke();
	}
	
}
