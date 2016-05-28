package com.tvd12.ezyfox.core.testing.clientresponseparser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.annotation.parser.ClientResponseParser;
import com.tvd12.test.base.BaseTest;
import com.tvd12.test.reflect.ReflectMethodUtil;

public class ClientResponseParserTest extends BaseTest {
    
    @Test
    public void hasNoAnnotationsTest() {
        assertFalse(ClientResponseParser.hasAnnotation(ClassD.class));
    }
    
    @Test
    public void hasAnnotationTest() {
        assertTrue(ClientResponseParser.hasAnnotation(ClassA.class));
    }
    
    @Test
    public void getCommandDefaultTest() {
        String command = ClientResponseParser.getCommand(
                ClassB.class, "xyz");
        assertEquals("xyz", command);
    }
    
    @Test
    public void getCommandHasTest() {
        String command = ClientResponseParser.getCommand(
                ClassA.class, "xyz");
        assertEquals("abc", command);
    }
    
    @Test
    public void getCommandByNoneTest() {
        String command = ClientResponseParser.getCommand(
                ClassC.class, "xyz");
        assertEquals("xyz", command);
    }
    
    @Test
    public void testConstructor() {
        Object object = ReflectMethodUtil.invokeConstructor(ClientResponseParser.class);
        assertNotNull(object);
    }
    
    @ClientResponseHandler(command = "abc")
    public static class ClassA {
        
    }
    
    @ClientResponseHandler
    public static class ClassB {
        
    }
    
    @ClientResponseHandler(command = "")
    public static class ClassC {
        
    }
    
    public static class ClassD {
        
    }
    
}
