package com.tvd12.ezyfox.core.testing.context;

import java.lang.reflect.Constructor;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.ExampleSFSZoneExtensionTest;
import com.tvd12.test.reflect.MethodInvoker;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AppContextImplTest2 {
    
    @Test
    public void test() {
        BaseAppContext context = createContext();
        context.initialize(ExampleSFSZoneExtensionTest.class);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testNewInstance() {
        BaseAppContext context = createContext();
        MethodInvoker.create()
            .object(context)
            .param(ClassA.class)
            .method("newInstance")
            .invoke();
        
    }
    
    private BaseAppContext createContext() {
        return new BaseAppContext() {
            
            @Override
            protected Constructor<?> getCommandConstructor(Class<?> commandClass) {
                return null;
            }
            
            @Override
            protected <T> T getCommand(Class<T> clazz) {
                return null;
            }
        };
    }
    
    @AllArgsConstructor
    @Data
    public static class ClassA {
        private String str;
    }
}
