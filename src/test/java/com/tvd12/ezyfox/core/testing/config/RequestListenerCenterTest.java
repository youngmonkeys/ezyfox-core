package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RequestListenerCenter;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.ExampleSFSZoneExtensionTest;
import com.tvd12.test.base.BaseTest;

public class RequestListenerCenterTest extends BaseTest {

    @Test
    public void testValidCase() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ExampleSFSZoneExtensionTest.class);
        ExtensionConfiguration config = loader.load();
        
        RequestListenerCenter center = new RequestListenerCenter();
        center.addListeners(config.getRequestResponseClientClasses());
        
        List<RequestResponseClass> classes = center.getListeners("abc");
        assertEquals(classes.size(), 3);
        
        Set<String> commands = center.getCommands();
        assertEquals(1, commands.size());
        
        assertEquals(0, center.getListeners("a").size());
    }
    
}
