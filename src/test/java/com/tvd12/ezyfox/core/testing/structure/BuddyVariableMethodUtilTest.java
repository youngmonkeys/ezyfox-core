package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.BuddyVariable;
import com.tvd12.ezyfox.core.annotation.BuddyVariableParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.structure.BuddyVariableMethodUtil;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.ReflectMethodUtil;

import lombok.Data;

public class BuddyVariableMethodUtilTest extends BaseTest {
    
    @Test
    public void checkHiddenTest1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("a1", ClassA.class);
        assertFalse(BuddyVariableMethodUtil.checkHidden(field, false));
        
        field = ReflectFieldUtil.getField("a0", ClassA.class);
        assertTrue(BuddyVariableMethodUtil.checkHidden(field, false));
        
        field = ReflectFieldUtil.getField("a4", ClassA.class);
        assertFalse(BuddyVariableMethodUtil.checkHidden(field, false));
    }
    
    @Test
    public void getKeyFromVariableParamAnnotationTest1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("a2", ClassA.class);
        String value = (String) ReflectMethodUtil
                .invokeStaticMethod("getKeyFromVariableParamAnnotation", 
                        BuddyVariableMethodUtil.class, 
                        field);
        assertEquals(value, "a22");
    }
    
    @Test
    public void getKeyFromVariableParamAnnotationTest2() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("a3", ClassA.class);
        assertFalse(BuddyVariableMethodUtil.checkHidden(field, false));
        String value = (String) ReflectMethodUtil
                .invokeStaticMethod("getKeyFromVariableParamAnnotation", 
                        BuddyVariableMethodUtil.class, 
                        field);
        assertEquals(value, "a33");
    }
    
    @Test
    public void testCheckHiddenWithMethod() {
        Method setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setName")
                .argument(String.class)
                .build();
        boolean hidden = BuddyVariableMethodUtil.checkHidden(setterMethod, true);
        assertTrue(hidden);
        
        Method getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        hidden = BuddyVariableMethodUtil.checkHidden(getterMethod, true);
        assertTrue(hidden);
        
        setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setHello")
                .argument(String.class)
                .build();
        hidden = BuddyVariableMethodUtil.checkHidden(setterMethod, true);
        assertFalse(hidden);
        
        getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getHello")
                .build();
        hidden = BuddyVariableMethodUtil.checkHidden(getterMethod, false);
        assertFalse(hidden);
    }
    
    public void getKeyTest() {
        
    }
    
    @Test
    public void testGetKeyByMethod() {
        Method getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        String key =  BuddyVariableMethodUtil.getKey(getterMethod, "abc");
        assertEquals(key, "abc");
        
        Method setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setName")
                .argument(String.class)
                .build();
        key = BuddyVariableMethodUtil.getKey(setterMethod, "abc");
        assertEquals(key, "hello");
        
        getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getValue")
                .build();
        key =  BuddyVariableMethodUtil.getKey(getterMethod, "abc");
        assertEquals(key, "abc");
        
        setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setValue")
                .argument(String.class)
                .build();
        key = BuddyVariableMethodUtil.getKey(setterMethod, "abc");
        assertEquals(key, "value");
        
        
    }
    
    @Test
    public void getKeyByFieldTest() {
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a0"), "0"), "0");
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a1"), "1"), "1");
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a2"), "0"), "2");
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a3"), "0"), "3");
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a4"), "4"), "a4");
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a5"), "0"), "5");
        assertEquals(BuddyVariableMethodUtil.getKey(getFieldInClassB("a6"), "0"), "6");
    }
    
    public Field getFieldInClassB(String name) {
        try {
            return ReflectFieldUtil.getField(name, ClassB.class);
        } catch (ExtensionException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Class<?> getTestClass() {
        return BuddyVariableMethodUtil.class;
    }
    
    @Data
    public static class ClassA {
        @BuddyVariable
        public String a0;
        
        public String a1;
        
        @BuddyVariableParam(name = "a22")
        public String a2;
        
        @BuddyVariableParam(value = "a33")
        public String a3;
        
        @BuddyVariable(visible = true)
        public String a4;
        
        @BuddyVariable(name = "hello", visible = false)
        public void setName(String name) {
            
        }
        
        @BuddyVariable(visible = false)
        public String getName1() {
            return "";
        }
        
        public String getName() {
            return null;
        }
        
        @BuddyVariable(visible = true)
        public void setHello(String hello) {
            
        }
        
        public String getHello() {
            return null;
        }
        
        @BuddyVariable("value")
        public void setValue(String value) {
            
        }
        
        public String getValue() {
            return null;
        }
    }
    
    @Data
    public static class ClassB {
        private String a0;
        
        @BuddyVariable
        private String a1;
        
        @BuddyVariable("2")
        private String a2;
        
        @BuddyVariable(name = "3")
        private String a3;
        
        @BuddyVariableParam
        private String a4;
        
        @BuddyVariableParam("5")
        private String a5;
        
        @BuddyVariableParam(name = "6")
        private String a6;
    }
}
