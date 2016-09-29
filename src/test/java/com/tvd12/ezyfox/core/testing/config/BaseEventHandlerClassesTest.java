package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.BaseEventHandlerClasses;
import com.tvd12.ezyfox.core.config.ServerEventHandlerClasses;
import com.tvd12.test.base.BaseTest;

public class BaseEventHandlerClassesTest extends BaseTest {
    
    @Test
    public void addHandlerTest() {
        BaseEventHandlerClasses handler = new ServerEventHandlerClasses();
        handler.addHandler("1", ClassA.class);
        handler.addHandler("1", ClassB.class);
        assertEquals(ClassB.class, handler.getHandlers("1").get(0));
        assertEquals(ClassA.class, handler.getHandlers("1").get(1));
        
        Set<Class<?>> classes = new HashSet<>();
        classes.add(ClassC.class);
        handler.addHandlers(classes);
        
        assertEquals(ClassC.class, handler.getHandlers("1").get(2));
        
        List<Class<?>> handlers = handler.getHandlers("2");
        assertEquals(0, handlers.size());
        
        Set<String> commands = handler.getEvents();
        assertTrue(commands.size() > 0);
    }
    
    @ServerEventHandler(event = "1", priority = 2)
    public static class ClassA {
        
    }
    
    @ServerEventHandler(event = "1", priority = 1)
    public static class ClassB {
        
    }
    
    @ServerEventHandler(event = "1", priority = 3)
    public static class ClassC {
        
    }
    
    
    
}
