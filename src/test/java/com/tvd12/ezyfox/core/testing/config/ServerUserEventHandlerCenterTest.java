package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ServerUserEventHandlerCenter;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.structure.ServerUserHandlerClass;

public class ServerUserEventHandlerCenterTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testValidCase() {
        ServerUserEventHandlerCenter center = new ServerUserEventHandlerCenter();
        List<ServerUserHandlerClass> classes = 
                center.addHandlers(
                        (List)Lists.newArrayList(ClassA.class, ClassB.class),
                        ExUser.class,
                        (List)Lists.newArrayList());
        assertEquals(classes.size(), 2);
        assertEquals(center.getHandlers().size(), 2);
    }
    
    
    public static class ExUser extends ApiUser {
        
    }
    
    public static class ExZone {
        
    }
    
    @ServerEventHandler(event = "A", priority = 2)
    public static class ClassA {
        
        public void handle(AppContext context,  ExUser user) {
            
        }
        
    }
    
    @ServerEventHandler(event = "B", priority = 1)
    public static class ClassB {
        
        public void handle(AppContext context, ExUser user) {
            
        }
        
    }
}
