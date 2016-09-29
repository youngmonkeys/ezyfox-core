package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.test.reflect.MethodBuilder;
import com.tvd12.test.reflect.MethodInvoker;

public class ServerHandlerClassTest {
    
    @Test
    public void testValidCase() {
        ServerHandlerClass clazz = new ServerHandlerClass(ClassA.class);
        assertEquals(clazz.getEventName(), "Login");
        assertEquals(clazz.getHandleMethod().getName(), "handle");
        assertNotNull(clazz.newInstance());
        
        Method method = MethodBuilder.create()
                .clazz(ServerHandlerClass.class)
                .method("checkHandleMethod")
                .argument(Class.class)
                .argument(Class[].class)
                .build();
        
        clazz = new ServerHandlerClass(ClassA.class);
        MethodInvoker.create()
            .object(clazz)
            .method(method)
            .param(ClassA.class)
            .param(new Class<?>[] {String.class})
            .invoke();
        
        clazz = new ServerHandlerClass(ClassA.class);
        MethodInvoker.create()
            .object(clazz)
            .method(method)
            .param(ClassC.class)
            .param((Class<?>[])null)
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testInvalidCase() {
        ServerHandlerClass clazz = new ServerHandlerClass(ClassB.class);
        clazz.newInstance();
    }

    @ServerEventHandler(event = "Login")
    public static class ClassA {
        public void handle(AppContext context) {
            
        }
        
    }
    
    @ServerEventHandler(event = "Login")
    public static class ClassB {
        
        private ClassB() {}
        
        public void handle(AppContext context) {
            
        }
    }
    
    @ServerEventHandler(event = "abc")
    public static class ClassC {
        public void handle(AppContext context, String username) {
            
        }
    }
    
}
