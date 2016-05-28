package com.tvd12.ezyfox.core.testing.reflectutil;

import static org.testng.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class RelectMethodUtilTest extends BaseTest {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(RelectMethodUtilTest.class);
    @SuppressWarnings("unused")
    @Test
    public void callSetterMethodByNamePerformanceTest() throws ExtensionException {
        ClassA classA = new ClassA();
        long currentMilis = System.currentTimeMillis();
        for(int i = 0 ; i < 10 ; i++) {
            String name = (String)ReflectMethodUtil.invokeGetterMethod(classA, "getName");
        }
        long offset1 = System.currentTimeMillis() - currentMilis;
        
        currentMilis = System.currentTimeMillis();
        for(int i = 0 ; i < 10 ; i++) {
            String name = classA.getName();
        }
        long offset2 = System.currentTimeMillis() - currentMilis;
//        assertNotEquals(offset1, offset2);
        LOGGER.info("callSetterMethodByNamePerformanceTest#offset1 = " + offset1);
        LOGGER.info("callSetterMethodByNamePerformanceTest#offset2 = " + offset2);
    }
    
    @SuppressWarnings("unused")
    @Test
    public void callGetterPerformanceTest() throws ExtensionException, NoSuchFieldException, SecurityException {
        ClassA classA = new ClassA();
        Field field = ClassA.class.getDeclaredField("name");
        long currentMilis = System.currentTimeMillis();
        for(int i = 0 ; i < 10 ; i++) {
            String name = (String)ReflectMethodUtil.invokeGetterMethod(classA, field);
        }
        long offset1 = System.currentTimeMillis() - currentMilis;
        
        currentMilis = System.currentTimeMillis();
        for(int i = 0 ; i < 10 ; i++) {
            String name = classA.getName();
        }
        long offset2 = System.currentTimeMillis() - currentMilis;
//        assertNotEquals(offset1, offset2);
        LOGGER.info("callGetterPerformanceTest#offset1 = " + offset1);
        LOGGER.info("callGetterPerformanceTest#offset2 = " + offset2);
    }
    
    @Override
    public Class<?> getTestClass() {
        return ReflectMethodUtil.class;
    }
    
    @Test
    public void invokeHandleMethodValidCase() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("handle")
                .argument(String.class)
                .build();
        ReflectMethodUtil.invokeHandleMethod(method, new ClassA(), "Hello");
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void invokeHandleMethodInvalidCase() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("handle")
                .argument(String.class)
                .build();
        ReflectMethodUtil.invokeHandleMethod(method, new ClassA(), "Hello", "World");
    }
    
    @Test
    public void invokeExecuteMethodValidCase() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("execute")
                .argument(String.class)
                .build();
        ReflectMethodUtil.invokeExecuteMethod(method, new ClassA(), "Hello");
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void invokeExecuteMethodInvalidCase() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("execute")
                .argument(String.class)
                .build();
        ReflectMethodUtil.invokeExecuteMethod(method, new ClassA(), "Hello", "World");
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testInvokeGetterMethodInvalidCase1() throws ExtensionException {
        ReflectMethodUtil.invokeGetterMethod(new ClassA(), "getName11");
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testInvokeGetterMethodOfFieldInvalidCase1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("value", ClassA.class);
        ReflectMethodUtil.invokeGetterMethod(new ClassA(), field);
    }
    
    @Test
    public void testInvokeSetterMethodValidCase() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("name", ClassA.class);
        ReflectMethodUtil.invokeSetterMethod(new ClassA(), field, "Hello");
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testInvokeSetterMethodInvalidCase() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("name", ClassA.class);
        ReflectMethodUtil.invokeSetterMethod(new ClassA(), field, new Integer(1));
    }
    
    @Test
    public void testInvokeSetterMethodWithNameValidCase() throws ExtensionException {
        ReflectMethodUtil.invokeSetterMethod(new ClassA(), "setName", "Hello");
    }
    
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testInvokeSetterMethodWithNameInvalidCase1() throws ExtensionException {
        ReflectMethodUtil.invokeSetterMethod(new ClassA(), "setName1", "Hello");
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testValidateSetterMethodInvalidCase1() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        ReflectMethodUtil.validateSetterMethod(method);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testValidateGetterMethodInvalidCase1() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setName")
                .argument(String.class)
                .build();
        ReflectMethodUtil.validateGetterMethod(method);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetGenericTypeInvalidCase1() throws ExtensionException {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        ReflectMethodUtil.getReturnGenericType(method);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetGenericTypeInvalidCase2() throws ExtensionException {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName1")
                .build();
        ReflectMethodUtil.getReturnGenericType(method);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetGenericTypeInvalidCase3() throws ExtensionException {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName2")
                .build();
        ReflectMethodUtil.getReturnGenericType(method);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void testGetMethodInvalidCase() throws ExtensionException {
        ReflectMethodUtil.getMethod("hello", ClassA.class);
    }
    
    @Test
    public void testGetMethodWithNameValidCase() {
        List<Method> methods = 
                ReflectMethodUtil.getMethodsWithName(ClassA.class, "getName");
        assertEquals(methods.size(), 1);
        
        methods = ReflectMethodUtil.getMethodsWithName(ClassA.class, "zzzz");
        assertEquals(methods.size(), 0);
    }
    
    @Test(expectedExceptions = {ExtensionException.class})
    public void invokeMethodStaticInvalidCaseTest() throws ExtensionException {
        Method method = MethodBuilder.create()
                .clazz(ClassC.class)
                .method("getValue")
                .build();
        method.setAccessible(false);
        ReflectMethodUtil.invokeMethod(method, null);
    }
    
    @Test
    public void testGetMethodWithFieldHasIsPrefix() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("isPublic", ClassD.class);
        Method method = ReflectFieldUtil.getGetterMethod(ClassD.class, field);
        assertEquals(method.getName(), "isPublic");
    }
    
    public static class ClassA {
        @Getter @Setter
        private String name;
        
        @Getter @Setter
        private Map<String, String> name1;
        
        @Getter @Setter
        private List<List<String>> name2;
        
        protected String value;
        
        @Getter
        protected String value1;
        
        public void setValue(String value) {
            
        }
        
        public String getValue() throws IllegalAccessException {
            throw new IllegalAccessException();
        }
        
        public void handle(String hello) {
            
        }
        
        public void execute(String hello) {
            
        }
    }
    
    public static class ClassB {
        
    }
    
    public static class ClassC {
        protected static String getValue() {
            return "hello";
        }
    }
    
    @Data
    public static class ClassD {
        private boolean isPublic;
    }
    
}
