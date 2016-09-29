package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.reflect.ReflectMethodUtil;
import com.tvd12.ezyfox.core.structure.GetterMethodCover;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

public class GetterMethodCoverTest {

    @Test
    public void testValidCase() {
        Method getAMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getA")
                .build();
        GetterMethodCover getter = new GetterMethodCover(ClassA.class, getAMethod);
        getter.setReturnClass(null);
        assertNull(getter.getReturnClass());
        
        Object value = getter.invoke(new ClassA());
        assertNull(value);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInValidCase1() {
        Method getBMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getB")
                .build();
        
        GetterMethodCover getter = new GetterMethodCover(ClassA.class, getBMethod);
        getter.invoke(new ClassB());
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInValidCase2() {
        Method getCMethod = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getC")
                .build();
        GetterMethodCover geter = new GetterMethodCover(ClassA.class, getCMethod);
        MethodInvoker.create()
            .object(geter)
            .method("getGenericTypeFromMethod")
            .param(getCMethod)
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInValidCase3() throws ExtensionException {
        Method getAMethod = ReflectMethodUtil.getMethod("getA", ClassA.class);
        GetterMethodCover geter = new GetterMethodCover(ClassA.class, getAMethod);
        Field field = ReflectFieldUtil.getField("z", ClassA.class);
        MethodInvoker.create()
            .object(geter)
            .method("getGetterMethodFromField")
            .param(ClassA.class)
            .param(field)
            .invoke();
    }
    
    public static class ClassA {
        
        protected String z;
        
        public String getA() {
            return null;
        }
        
        protected String getB() {
            return null;
        }
        
        public List<?> getC() {
            return null;
        }
        
    }
    
    public static class ClassB {
        
    }
}
