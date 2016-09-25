package com.tvd12.ezyfox.core.testing.entities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.ezyfox.core.structure.ResponseHandlerClass;

import lombok.Data;

import static org.testng.Assert.*;

public class RequestListenerClassTest {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(RequestListenerClassTest.class);
	
	@Test
	public void testValidCase() {
		ResponseHandlerClass listenerClass 
				= new ResponseHandlerClass(ClassC.class);
		assertEquals(4, listenerClass.methodCount());
		assertEquals("classA", listenerClass.getMethod(0).getKey());
		assertEquals("classBs", listenerClass.getMethod(1).getKey());
		assertEquals("value", listenerClass.getMethod(2).getKey());
		assertEquals("classC", listenerClass.getMethod(3).getKey());
		assertEquals(ClassA.class, listenerClass.getMethod(0).getReturnClass().getClazz());
		assertEquals(ClassB.class, listenerClass.getMethod(1).getReturnClass().getClazz());
		assertEquals(ClassC.class, listenerClass.getMethod(3).getReturnClass().getClazz());
		for(GetterMethodCover method : listenerClass.getMethods()) {
		    LOGGER.info(method.getKey() + ", " + method.getMethodName());
		}
	}

	@Data
	public static class ClassA {
		@ResponseParam
		public String name;
		
		@ResponseParam
        public ClassB classB;
		
		@ResponseParam
		public ClassC classC;
		
		@ResponseParam
        public ClassA classA;
	}
	@Data
	public static class ClassB {
	    @ResponseParam
		public String address;
		
	    @ResponseParam
		public ClassC classC;
		
	    @ResponseParam
        public ClassA classA;
		
	    @ResponseParam
        public ClassB classB;
	}
	@Data
	public static class ClassC {
	    @ResponseParam
		public ClassA classA;
		
	    @ResponseParam
		public List<ClassB> classBs;
		
	    @ResponseParam
		public int value;
		
	    @ResponseParam
        public ClassC classC;
		
		public String ignored;
	}
	
}
