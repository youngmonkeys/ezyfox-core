package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.exception.ExtensionException;
import com.tvd12.ezyfox.core.reflect.ReflectFieldUtil;
import com.tvd12.ezyfox.core.structure.ResponseParamMethod;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

public class ResponseParamMethodTest {

    @Test
    public void getKeyOnMethodTest() {
        Method method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setValue")
                .argument(String.class)
                .build();
        String key = MethodInvoker.create()
                .object(new ResponseParamMethodEx())
                .method("getKey")
                .param(method)
                .invoke(String.class);
        assertEquals(key, "val");
        
        method = MethodBuilder.create()
                .clazz(ClassA.class)
                .method("setName")
                .argument(String.class)
                .build();
        key = MethodInvoker.create()
                .object(new ResponseParamMethodEx())
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
                .object(new ResponseParamMethodEx())
                .method("getKey")
                .param(field)
                .invoke(String.class);
        assertEquals(key, "val");
        
        field = ReflectFieldUtil.getField("name", ClassA.class);
        key = MethodInvoker.create()
                .object(new ResponseParamMethodEx())
                .method("getKey")
                .param(field)
                .invoke(String.class);
        assertEquals(key, "name");
    }

    public static class ResponseParamMethodEx extends ResponseParamMethod {
        
        public ResponseParamMethodEx() {
            this(null, (Method)null);
        }
        
        public ResponseParamMethodEx(Class<?> clazz, Method method) {
            super(clazz, method);
        }
        
        @Override
        protected void initWithMethod(Class<?> clazz, Method method) {
            
        }
    }
    
    public static class ClassA {
        @ResponseParam("val")
        private String value;
        
        @ResponseParam("")
        private String name;
        
        @ResponseParam("val")
        public void setValue(String value) {
        }
        
        @ResponseParam("")
        public void setName(String name) {
        }
    }
    
}
