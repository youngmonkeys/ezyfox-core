package com.tvd12.ezyfox.core.testing.executionmethodparser;

import com.tvd12.ezyfox.core.annotation.ExecuteMethod;
import com.tvd12.ezyfox.core.content.AppContext;

public class ExampleListener {

	@ExecuteMethod
	public void doSomeThing(AppContext context, ExampleUser user) throws Exception {}
	
}
