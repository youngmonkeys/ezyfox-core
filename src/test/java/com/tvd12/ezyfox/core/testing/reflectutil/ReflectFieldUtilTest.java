package com.tvd12.ezyfox.core.testing.reflectutil;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.test.base.BaseTest;

import lombok.Data;

public class ReflectFieldUtilTest extends BaseTest{

	private static final Logger LOGGER
    		= LoggerFactory.getLogger(ReflectFieldUtilTest.class);
	
    @Test
    public void testGetGenerictypePerformance() throws ExtensionException, NoSuchFieldException, SecurityException {
        Field field = ClassA.class.getDeclaredField("abc");
        long currentMilis = System.currentTimeMillis();
        for(int i = 0 ; i < 1000 ; i++) {
            ReflectFieldUtil.getGenericType(field);
        }
        long offset1 = System.currentTimeMillis() - currentMilis;
        
        LOGGER.info("testGetGenerictypePerformance#offset1 = " + offset1);
    }
    
    @Test
    public void testGetClassHasNoAnnotationCase() {
    	List<Field> fields = ReflectFieldUtil.getFieldsWithAnnotation(ClassA.class,
    			ResponseParam.class);
    	assertEquals(fields.size(), 0);
    }
    
    @Test
	public void getFieldsWithAnnotationsPerformaceTest() throws ExtensionException {
		long time = System.currentTimeMillis();
		ClassB classB = new ClassB();
		for(int i = 0 ; i < 1000 ; i++) {
			List<Field> fields = ReflectFieldUtil.getFieldsWithAnnotation(
					ClassB.class, ResponseParam.class);
			for(Field field : fields) {
				Method method = ReflectFieldUtil.getGetterMethod(ClassB.class, field);
				ReflectMethodUtil.invokeMethod(method, classB);
			}
		}
		long offset = System.currentTimeMillis() - time;
		LOGGER.info("getFieldsWithAnnotationsPerformaceTest time = " + offset);
	}

    @Override
    public Class<?> getTestClass() {
        return ReflectFieldUtil.class;
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetFieldInvalidCase1() throws ExtensionException {
        ReflectFieldUtil.getField("zzz", ClassA.class);
    }
    
    @Test
    public void testNewInstanceValidCase() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("classB", ClassC.class);
        assertNotNull(ReflectFieldUtil.newInstance(field));
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testNewInstanceInvalidCase1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("classD", ClassC.class);
        assertNotNull(ReflectFieldUtil.newInstance(field));
    }
    
    @Test
    public void testBooleanGetterMethod() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("visible", ClassC.class);
        assertEquals(ReflectFieldUtil.getGetterMethod(ClassC.class, field).getName(), "isVisible");
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetGenericTypeInvalidCase1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("visible", ClassC.class);
        ReflectFieldUtil.getGenericType(field);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetGenericTypeInvalidCase2() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("map", ClassC.class);
        ReflectFieldUtil.getGenericType(field);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetGenericTypeInvalidCase3() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("list", ClassC.class);
        ReflectFieldUtil.getGenericType(field);
    }
    
    
    @Data
    public static class ClassA {
        private String name;
        private List<String> abc = new ArrayList<>();
    }
	
    @Data
    public static class ClassB {
    	@ResponseParam
    	private String name;
    	
    	private int a;
    	private int b;
    	private int c;
    	private int d;
    	private int e;
    	private int f;
    	private int g;
    	private int h;
    	private int i;
    	private int j;
    }
    
    @Data
    public static class ClassC {
        public ClassB classB; 
        public ClassD classD;
        public boolean visible;
        
        public Map<String, String> map;
        public List<List<String>> list;
    }
    
    public static class ClassD {
        public ClassD(String value) {
            
        }
    }
    
}
