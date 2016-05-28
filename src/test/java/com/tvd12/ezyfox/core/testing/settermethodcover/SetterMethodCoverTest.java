package com.tvd12.ezyfox.core.testing.settermethodcover;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.structure.SetterMethodCover;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

import static org.testng.Assert.*;

public class SetterMethodCoverTest {

    public void testValidCase() {
        Method setAMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setA")
                .argument(String.class)
                .build();
        SetterMethodCover setter = new SetterMethodCover(ClassA.class, setAMethod);
        setter.setParameterClass(null);
        assertNull(setter.getParameterClass());
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInValidCase1() {
        Method setBMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setB")
                .argument(String.class)
                .build();
        
        SetterMethodCover setter = new SetterMethodCover(ClassA.class, setBMethod);
        setter.invoke(new ClassA(), 1);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInValidCase2() {
        Method setDMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setD")
                .argument(List.class)
                .build();
        SetterMethodCover setter = new SetterMethodCover(ClassA.class, setDMethod);
        MethodInvoker.create()
            .object(setter)
            .method("getGenericTypeFromMethod")
            .param(setDMethod)
            .invoke();
    }
    
    public static class ClassA {
        
        public void setA(String a) {
            
        }
        
        protected void setB(String b) {
            
        }
        
        
        public void setAB(String a, String b) {
            
        }
        
        public void setD(List<?> d) {
            
        }
        
    }
    
}
