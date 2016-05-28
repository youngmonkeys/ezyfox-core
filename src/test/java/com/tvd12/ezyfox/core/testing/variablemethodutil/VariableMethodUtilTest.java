package com.tvd12.ezyfox.core.testing.variablemethodutil;

import static org.testng.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.Variable;
import com.tvd12.ezyfox.core.annotation.VariableParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.structure.VariableMethodUtil;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.ReflectMethodUtil;

import lombok.Data;

public class VariableMethodUtilTest extends BaseTest {
    
    @Test
    public void checkHiddenTest1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("a1", ClassA.class);
        assertFalse(VariableMethodUtil.checkHidden(field, false));
        
        field = ReflectFieldUtil.getField("a0", ClassA.class);
        assertTrue(VariableMethodUtil.checkHidden(field, false));
        
        field = ReflectFieldUtil.getField("a4", ClassA.class);
        assertFalse(VariableMethodUtil.checkHidden(field, false));
    }
    
    @Test
    public void getKeyFromVariableParamAnnotationTest1() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("a2", ClassA.class);
        String value = (String) ReflectMethodUtil
                .invokeStaticMethod("getKeyFromVariableParamAnnotation", 
                        VariableMethodUtil.class, 
                        field);
        assertEquals(value, "a22");
    }
    
    @Test
    public void getKeyFromVariableParamAnnotationTest2() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("a3", ClassA.class);
        assertFalse(VariableMethodUtil.checkHidden(field, false));
        String value = (String) ReflectMethodUtil
                .invokeStaticMethod("getKeyFromVariableParamAnnotation", 
                        VariableMethodUtil.class, 
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
        boolean hidden = VariableMethodUtil.checkHidden(setterMethod, true);
        assertTrue(hidden);
        
        Method getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        hidden = VariableMethodUtil.checkHidden(getterMethod, true);
        assertTrue(hidden);
        
        setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setHello")
                .argument(String.class)
                .build();
        hidden = VariableMethodUtil.checkHidden(setterMethod, true);
        assertFalse(hidden);
        
        getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getHello")
                .build();
        hidden = VariableMethodUtil.checkHidden(getterMethod, false);
        assertFalse(hidden);
    }
    
    @Test
    public void testGetKeyByMethod() {
        Method getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        String key =  VariableMethodUtil.getKey(getterMethod, "abc");
        assertEquals(key, "abc");
        
        Method setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setName")
                .argument(String.class)
                .build();
        key = VariableMethodUtil.getKey(setterMethod, "abc");
        assertEquals(key, "hello");
        
        getterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getValue")
                .build();
        key =  VariableMethodUtil.getKey(getterMethod, "abc");
        assertEquals(key, "abc");
        
        setterMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setValue")
                .argument(String.class)
                .build();
        key = VariableMethodUtil.getKey(setterMethod, "abc");
        assertEquals(key, "value");
        
        
    }
    
    @Override
    public Class<?> getTestClass() {
        return VariableMethodUtil.class;
    }
    
    @Data
    public static class ClassA {
        @Variable
        public String a0;
        
        public String a1;
        
        @VariableParam(name = "a22")
        public String a2;
        
        @VariableParam(value = "a33")
        public String a3;
        
        @Variable(visible = true)
        public String a4;
        
        @Variable(name = "hello", visible = false)
        public void setName(String name) {
            
        }
        
        public String getName() {
            return null;
        }
        
        @Variable(visible = true)
        public void setHello(String hello) {
            
        }
        
        public String getHello() {
            return null;
        }
        
        @VariableParam("value")
        public void setValue(String value) {
            
        }
        
        public String getValue() {
            return null;
        }
    }
}
