package com.tvd12.ezyfox.core.testing.executionmethodparser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ExecuteMethod;
import com.tvd12.ezyfox.core.annotation.parser.ExecutionMethodParser;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.test.reflect.ReflectMethodUtil;

public class ExecutionMethodParserTest {
	
	@Test
	public void testValidCase() throws ExtensionException {
		Method method1 = ExecutionMethodParser
		        .getListenerExecuteMethod(BettingActionListener.class, ExampleUser.class, new ArrayList<Class<?>>());
		assertEquals("execute", method1.getName());
		Method method2 = ExecutionMethodParser
		        .getListenerExecuteMethod(ExampleListener.class, ExampleUser.class, new ArrayList<Class<?>>());
		assertEquals("doSomeThing", method2.getName());
	}
	
	@Test(expectedExceptions = {RuntimeException.class})
	public void testInvalidCase() throws ExtensionException {
		ExecutionMethodParser
		.getListenerExecuteMethod(getClass(), ExampleUser.class, new ArrayList<Class<?>>());
	}
	
	@Test(expectedExceptions = {RuntimeException.class})
    public void testWrongParametersCase() throws ExtensionException {
        ExecutionMethodParser.getListenerExecuteMethod(ClassA.class, ExampleUser.class, new ArrayList<Class<?>>());
    }
	
	@Test
    public void testConstructor() {
        Object object = ReflectMethodUtil.invokeConstructor(ExecutionMethodParser.class);
        assertNotNull(object);
    }
	
	@Test
    public void testInvalidCase1() throws ExtensionException {
        Method method = ExecutionMethodParser.getListenerExecuteMethod(
                ClassB.class, ExampleUser.class, new ArrayList<Class<?>>());
        assertNotNull(method);
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void testGetExecuteMethodContainGameUserCase() {
	    Method method = ExecutionMethodParser.getListenerExecuteMethod(
                ClassB.class, 
                ExampleUser.class, 
                (List)Lists.newArrayList(PokerUser.class));
        assertNotNull(method);
	}
	
	public static class ClassA {
	    public void execute(String name) {
	        
	    }
	    
	    public void execute(String name, String value) {
	        
	    }
	    
	    @ExecuteMethod
	    public void listen(String name, String value) {
	        
	    }
	    
	    public void execute(AppContext context, String name) {
	        
	    }
	}
	
	public static class ClassB {
	    @ExecuteMethod
	    public void perform(String a) {
	        
	    }
	    
	    @ExecuteMethod
        public void perform(String a, String b) {
            
        }
	    
	    @ExecuteMethod
        public void perform(AppContext a, String b) {
            
        }
	    
	    public void execute(AppContext context, ExampleUser user) throws Exception {
            
        }
	}
	
	public static class BettingActionListener {
		public void execute(AppContext context, ApiUser user) throws Exception {
			
		}
	}
	
	public static class StateActionListener {
	    public void execute(AppContext context, PokerUser user) throws Exception {
            
        }
	}
	
	public static class PokerUser {
	    
	}
	
}
