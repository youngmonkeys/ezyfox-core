package com.tvd12.ezyfox.core.testing.baseeventhandlerclasses;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.BaseEventHandlerClasses;
import com.tvd12.ezyfox.core.config.ServerEventHandlerClasses;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

public class BaseEventHandlerClassesTest extends BaseTest {
    
    @Test
    public void addHandlerTest() {
        BaseEventHandlerClasses handler = new ServerEventHandlerClasses();
        handler.addHandler("1", ClassA.class);
        handler.addHandler("1", ClassB.class);
        assertEquals(ClassB.class, handler.getHandlers("1").get(0));
        assertEquals(ClassA.class, handler.getHandlers("1").get(1));
        
        List<Class<?>> classes = new ArrayList<>();
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
