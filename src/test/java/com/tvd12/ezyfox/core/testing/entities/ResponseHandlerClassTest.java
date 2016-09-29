package com.tvd12.ezyfox.core.testing.entities;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.structure.RequestListenerClass;
import com.tvd12.ezyfox.core.structure.SetterMethodCover;

import lombok.Data;

import static org.testng.Assert.*;

public class ResponseHandlerClassTest {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(ResponseHandlerClassTest.class);
	
	@Test
	public void testValidCase() {
		RequestListenerClass listenerClass 
				= new RequestListenerClass(ClassC.class);
		assertEquals(4, listenerClass.methodCount());
		assertEquals("classA", listenerClass.getMethod(0).getKey());
		assertEquals("classBs", listenerClass.getMethod(1).getKey());
		assertEquals("value", listenerClass.getMethod(2).getKey());
		assertEquals("classC", listenerClass.getMethod(3).getKey());
		assertEquals(ClassA.class, listenerClass.getMethod(0).getParameterClass().getClazz());
		assertEquals(ClassB.class, listenerClass.getMethod(1).getParameterClass().getClazz());
		assertEquals(ClassC.class, listenerClass.getMethod(3).getParameterClass().getClazz());
		for(SetterMethodCover method : listenerClass.getMethods()) {
		    LOGGER.info(method.getKey() + ", " + method.getMethodName());
		}
	}

	@Data
	public static class ClassA {
		@RequestParam
		public String name;
		
		@RequestParam
        public ClassB classB;
		
		@RequestParam
		public ClassC classC;
		
		@RequestParam
        public ClassA classA;
	}
	@Data
	public static class ClassB {
		@RequestParam
		public String address;
		
		@RequestParam
		public ClassC classC;
		
		@RequestParam
        public ClassA classA;
		
		@RequestParam
        public ClassB classB;
	}
	@Data
	public static class ClassC {
		@RequestParam
		public ClassA classA;
		
		@RequestParam
		public List<ClassB> classBs;
		
		@RequestParam
		public int value;
		
		@RequestParam
        public ClassC classC;
		
		public String ignored;
	}
	
}
