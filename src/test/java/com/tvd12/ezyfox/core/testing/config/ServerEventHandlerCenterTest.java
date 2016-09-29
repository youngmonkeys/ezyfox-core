package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.ExampleSFSZoneExtensionTest;
import com.tvd12.test.base.BaseTest;

public class ServerEventHandlerCenterTest extends BaseTest {

    @Test
    public void testValidCase() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ExampleSFSZoneExtensionTest.class);
        ExtensionConfiguration config = loader.load();
        ServerEventHandlerCenter center = new ServerEventHandlerCenter();
        
        List<ServerHandlerClass> handlers = center.addHandlers(config.getServerEventHandlerClasses());
        assertEquals(handlers.size(), 3);
    }
    
}
