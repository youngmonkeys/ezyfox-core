package com.tvd12.ezyfox.core.testing.parser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.annotation.parser.ServerEventParser;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.ReflectMethodUtil;

public class ServerEventParserTest extends BaseTest {

    @Test
    public void test() {
        String eventName = ServerEventParser.getEventName(ClassA.class);
        int priority = ServerEventParser.getEventPriority(ClassA.class);
        assertEquals("USER_LOGIN", eventName);
        assertEquals(1, priority);
    }
    
    @Test
    public void testConstructor() {
        Object object = ReflectMethodUtil.invokeConstructor(ServerEventParser.class);
        assertNotNull(object);
    }
    
    @ServerEventHandler(event = "USER_LOGIN", priority = 1)
    public static class ClassA {
        
    }
    
}
