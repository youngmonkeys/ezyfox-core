package com.tvd12.ezyfox.core.test;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.EzyBadRequestException;
import com.tvd12.test.base.BaseTest;

public class EzyBadRequestExceptionTest extends BaseTest {

	@Test
	public void test() {
		EzyBadRequestException exception = new EzyBadRequestException(1, "hello", true, new Exception());
		assert exception.getCode() == 1;
		assert exception.getMessage().equals("hello");
		assert exception.getReason().equals("hello");
		assert exception.isSendToClient();
		new EzyBadRequestException("hello", 1, true, new Exception());
		new EzyBadRequestException("hello", 1, true);
		new EzyBadRequestException("hello", 1);
		new EzyBadRequestException(1, "hello", true, new Exception());
		new EzyBadRequestException(1, "hello", true);
		new EzyBadRequestException(1, "hello");
		new EzyBadRequestException(1);
		new EzyBadRequestException(1, true);
		new EzyBadRequestException();
		new EzyBadRequestException("hello");
		new EzyBadRequestException("hello", new Exception());
		new EzyBadRequestException("hello", 1, new Exception());
		new EzyBadRequestException(1, "hello", new Exception());
		new EzyBadRequestException(1, true, new Exception());
	}
	
}
