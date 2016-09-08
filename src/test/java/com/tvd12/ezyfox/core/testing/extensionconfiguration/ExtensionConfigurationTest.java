package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.test.reflect.MethodInvoker;

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
        assertEquals(config.getRequestResponseClientClasses().size(), 4);
        assertEquals(config.getServerEventHandlerClasses().size(), 4);
        assertEquals(ExampleRoom.class, config.getRoomAgentClasses()
                .get(ExampleRoom.class).getUnwrapper().getClazz());
        assertEquals(config.getGameUserClasses().size(), 2);
        assertEquals(config.getGameUserAgentClasses().size(), 2);
        assertEquals(config.getResponseParamsClasses().size(), 1);
        assertEquals(config.getMessageParamsClasses().size(), 1);
        assertEquals(config.getMessageParamsClasses()
                .get(ExMessagesParameter.class).getWrapper().methodCount(), 1);
        assertEquals(config.getObjectDeserializerClasses().size(), 2);
        assertEquals(config.getObjectSerializerClasses().size(), 2);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testInvalidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        config.load(ExampleSFSZoneExtensionTest2.class);
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkUserAgentClassTest() {
        ExtensionConfiguration config = new ExtensionConfiguration() {
            @Override
            public Class<?> getUserClass() {
                return ClassA.class;
            }
        };
        MethodInvoker.create()
            .object(config)
            .method("checkUserAgentClass")
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckRoomClassInvalidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        MethodInvoker.create()
            .object(config)
            .method("checkRoomClass")
            .param(ClassA.class)
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckGameUserClassInvalidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        MethodInvoker.create()
            .object(config)
            .method("checkGameUserClass")
            .param(ClassA.class)
            .invoke();
    }
    
    public static class ClassA {
        
    }
}
