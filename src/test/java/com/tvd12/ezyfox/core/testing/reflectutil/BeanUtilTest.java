package com.tvd12.ezyfox.core.testing.reflectutil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.test.performance.Performance;
import com.tvd12.test.performance.Script;

import lombok.Data;

public class BeanUtilTest {

	private static final Logger LOGGER
		= LoggerFactory.getLogger(BeanUtilTest.class);
	
	@Test
	public void testPerform() {
		final ClassA classA = new ClassA();
		long time = Performance.create()
				.test(new Script() {
                    public void execute() {
                        try {
                            BeanUtils.getProperty(classA, "name");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
					
				}).getTime();
		LOGGER.info("BeanUtilTest#testPerform time = " + time);
	}
	
	@Data
	public static class ClassA {
		public String name = "Dung";
		public List<ClassB> list = new ArrayList<>();
		public List<String> strs = new ArrayList<>();
	}
	
	@Data
	public static class ClassB {
		public String des = "description";
	}
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
	}
	
}
