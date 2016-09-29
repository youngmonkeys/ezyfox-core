package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.AppExtensionConfigurationImpl;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.test.reflect.MethodInvoker;

public class ExtensionConfigurationTest {

    @Test
    public void testValidCase() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ExampleSFSZoneExtensionTest.class);
        AppExtensionConfigurationImpl config = loader.load();
        
        assertEquals(ExampleUser.class, config.getUserClass());
        assertEquals(ExampleUser.class, config.getUserAgentClass().getWrapper().getClazz());
        assertEquals(config.getRequestResponseClientClasses().size(), 3);
        assertEquals(config.getServerEventHandlerClasses().size(), 3);
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
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ExampleSFSZoneExtensionTest2.class);
        loader.load();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkUserAgentClassTest() {
        AppExtensionConfigurationImpl config = new AppExtensionConfigurationImpl() {
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
        AppExtensionConfigurationImpl config = new AppExtensionConfigurationImpl();
        MethodInvoker.create()
            .object(config)
            .method("checkRoomClass")
            .param(ClassA.class)
            .invoke();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void testCheckGameUserClassInvalidCase() {
        AppExtensionConfigurationImpl config = new AppExtensionConfigurationImpl();
        MethodInvoker.create()
            .object(config)
            .method("checkGameUserClass")
            .param(ClassA.class)
            .invoke();
    }
    
    public static class ClassA {
        
    }
}
