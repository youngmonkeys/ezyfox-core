package com.tvd12.ezyfox.core.testing.requestresponseclass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;

public class RequestResponseClassTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testValidCase() {
        RequestResponseClass clazz = 
                new RequestResponseClass(ClassA.class, 
                ExampleUser.class, 
                (List)Lists.newArrayList(PokerUser.class));
        assertEquals(clazz.getResponseCommand(), "B");
        assertEquals(clazz.getExecuteMethod().getName(), "execute");
        assertEquals(clazz.getRequestListenerClass().getClazz(), ClassA.class);
        assertEquals(clazz.getResponseHandlerClass().getClazz(), ClassA.class);
        assertNotNull(clazz.newInstance());
        assertEquals(clazz.getUserClass(), ExampleUser.class);
        
        clazz = new RequestResponseClass(ClassB.class, 
                ExampleUser.class, 
                (List)Lists.newArrayList(PokerUser.class));
        assertEquals(clazz.getExecuteMethod().getName(), "execute");
    }
    
    
    @ClientRequestListener(command = "A")
    @ClientResponseHandler(command = "B")
    public static class ClassA {
        public void execute(AppContext context, ExampleUser user) {
            
        }
    }
    
    @ClientRequestListener(command = "A")
    @ClientResponseHandler(command = "B")
    public static class ClassB {
        public void execute(AppContext context, PokerUser user) {
            
        }
    } 
    
    public static class ExampleUser extends ApiUser {
        
    }
    
    public static class PokerUser extends ApiGameUser {
        
    }
}
