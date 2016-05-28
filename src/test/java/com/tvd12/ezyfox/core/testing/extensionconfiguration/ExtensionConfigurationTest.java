package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.test.reflect.MethodInvoker;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class ExtensionConfigurationTest {

    @Test
    public void testValidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        config.load(ExampleSFSZoneExtensionTest.class);
        
        assertEquals(ExampleRoom.class, config.getRoomClasses().get(0));
        assertEquals(ExampleUser.class, config.getUserClass());
        assertEquals(ExampleRoom.class, config.getRoomAgentClasses()
                .get(ExampleRoom.class).getWrapper().getClazz());
        assertEquals(ExampleUser.class, config.getUserAgentClass().getWrapper().getClazz());
        assertEquals(2, config.getRequestResponseClientClasses().size());
        assertEquals(2, config.getServerEventHandlerClasses().size());
        assertEquals(ExampleRoom.class, config.getRoomAgentClasses()
                .get(ExampleRoom.class).getUnwrapper().getClazz());
        assertEquals(config.getGameUserClasses().size(), 2);
        assertEquals(config.getGameUserAgentClasses().size(), 2);
        assertEquals(config.getResponseParamsClasses().size(), 1);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testInvalidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        config.load(ExampleSFSZoneExtensionTest2.class);
    }
    
    @SuppressWarnings("unchecked")
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkUserAgentClassTest() {
        ExtensionConfiguration config = mock(ExtensionConfiguration.class);
        when((Class<ClassA>)config.getUserClass()).thenReturn(ClassA.class);
        MethodInvoker.create()
            .object(config)
            .method("checkUserAgentClass")
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckRoomClassInvalidCase() {
        ExtensionConfiguration config = mock(ExtensionConfiguration.class);
        MethodInvoker.create()
            .object(config)
            .method("checkRoomClass")
            .param(ClassA.class)
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckGameUserClassInvalidCase() {
        ExtensionConfiguration config = mock(ExtensionConfiguration.class);
        MethodInvoker.create()
            .object(config)
            .method("checkGameUserClass")
            .param(ClassA.class)
            .invoke();
    }
    
    public static class ClassA {
        
    }
}
