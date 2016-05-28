package com.tvd12.ezyfox.core.testing.requestparammethod;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RequestParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.structure.RequestParamMethod;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

public class RequestParamMethodTest {
    
    @Test
    public void getKeyOnMethodTest() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getValue")
                .build();
        String key = MethodInvoker.create()
                .object(new RequestParamMethodEx())
                .method("getKey")
                .param(method)
                .invoke(String.class);
        assertEquals(key, "val");
        
        method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("getName")
                .build();
        key = MethodInvoker.create()
                .object(new RequestParamMethodEx())
                .method("getKey")
                .param(method)
                .invoke(String.class);
        assertEquals(key, "name");
    }
    
    @Test
    public void getKeyOnFieldTest() throws ExtensionException {
        Field field = ReflectFieldUtil.getField("value", ClassA.class);
        field.setAccessible(true);
        String key = MethodInvoker.create()
                .object(new RequestParamMethodEx())
                .method("getKey")
                .param(field)
                .invoke(String.class);
        assertEquals(key, "val");
        
        field = ReflectFieldUtil.getField("name", ClassA.class);
        key = MethodInvoker.create()
                .object(new RequestParamMethodEx())
                .method("getKey")
                .param(field)
                .invoke(String.class);
        assertEquals(key, "name");
    }

    public static class RequestParamMethodEx extends RequestParamMethod {
        
        public RequestParamMethodEx() {
            this(null, (Method)null);
        }
        
        public RequestParamMethodEx(Class<?> clazz, Method method) {
            super(clazz, method);
        }
        
        @Override
        protected void initWithMethod(Class<?> clazz, Method method) {
            
        }
    }
    
    public static class ClassA {
        @RequestParam("val")
        private String value;
        
        @RequestParam("")
        private String name;
        
        @RequestParam("val")
        public String getValue() {
            return null;
        }
        
        @RequestParam("")
        public String getName() {
            return null;
        }
    }
    
}
