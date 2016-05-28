package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;

public class ServerUserHandlerClassTest {

    @Test
    public void testValidCase() {
        ServerUserHandlerClass clazz = new ServerUserHandlerClass(
                ClassA.class, ExUser.class, new ArrayList<Class<?>>());
        assertEquals(clazz.getUserClass(), ExUser.class);
        assertEquals(clazz.getHandleMethod().getName(), "handle");
    }
    
    public static class ExUser extends ApiUser {
        
    }
    
    @ServerEventHandler(event = "A")
    public static class ClassA {
        public void handle(AppContext context, ExUser user) {
            
        }
    }
    
    @ServerEventHandler(event = "B")
    public static class ClassB {
        public void handle(AppContext context, ExUser user) {
            
        }
    }
    
}
