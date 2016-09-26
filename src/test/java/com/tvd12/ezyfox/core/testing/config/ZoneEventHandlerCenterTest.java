package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.ZoneEventHandlerCenter;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiZone;
import com.tvd12.ezyfox.core.structure.ZoneHandlerClass;

public class ZoneEventHandlerCenterTest {
    
    @Test
    public void testValidCase() {
        Set<Class<?>> handlerClasses = new HashSet<>();
        handlerClasses.add(ClassA.class);
        handlerClasses.add(ClassB.class);
        ZoneEventHandlerCenter center = new ZoneEventHandlerCenter();
        List<ZoneHandlerClass> handlers = center.addHandlers(
                handlerClasses, ExampleUser.class, new HashSet<Class<?>>());
        assertEquals(2, handlers.size());
        assertEquals(2, center.getHandlers().size());
    }

    public static class ExampleUser {
        
    }
    
    @ServerEventHandler(event="USER_JOIN_ZONE", priority = 2)
    public static class ClassA {
        public void handle(AppContext context, ApiZone zone, ExampleUser user) {
            
        }
    }
    
    @ServerEventHandler(event="USER_JOIN_ZONE", priority = 1)
    public static class ClassB {
        public void handle(AppContext context, ApiZone zone, ExampleUser user) {
            
        }
    }
    
}
