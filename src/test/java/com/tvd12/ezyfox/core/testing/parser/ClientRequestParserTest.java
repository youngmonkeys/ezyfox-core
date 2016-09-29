package com.tvd12.ezyfox.core.testing.parser;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.parser.ClientRequestParser;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.ReflectMethodUtil;

public class ClientRequestParserTest extends BaseTest {

    
    @Test
    public void testPerformance() {
        long time = System.currentTimeMillis();
        for(int i = 0 ; i < 1000000 ; i++) {
            ClientRequestParser.getCommand(ClassA.class);
        }
        long offset = System.currentTimeMillis() - time;
        LOGGER().info("TestPerformance#time = " + offset);
    }
    
    @Test
    public void testValidCase() {
        String command = ClientRequestParser.getCommand(ClassA.class);
        assertEquals("abc", command);
    }
    
    @Test(expectedExceptions = NullPointerException.class)
    public void testInvalidCase() {
        ClientRequestParser.getCommand(Class.class);
    }
    
    @Test
    public void getPriorityTest() {
        int priority = ClientRequestParser.getPriority(ClassA.class);
        assertEquals(1, priority);
    }
    
    @Test
    public void testConstructor() {
        Object object = ReflectMethodUtil.invokeConstructor(ClientRequestParser.class);
        assertNotNull(object);
    }
    
    @ClientRequestListener(command = "abc", priority = 1)
    public static class ClassA {
        
    }
}
