package com.tvd12.ezyfox.core.testing.servereventhandlercenter;

import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.ServerEventHandlerCenter;
import com.tvd12.ezyfox.core.structure.ServerHandlerClass;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.ExampleSFSZoneExtensionTest;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

public class ServerEventHandlerCenterTest extends BaseTest {

    @Test
    public void testValidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        config.load(ExampleSFSZoneExtensionTest.class);
        ServerEventHandlerCenter center = new ServerEventHandlerCenter();
        
        List<ServerHandlerClass> handlers = center.addHandlers(config.getServerEventHandlerClasses());
        assertEquals(handlers.size(), 3);
    }
    
}
