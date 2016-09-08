package com.tvd12.ezyfox.core.testing.requestlistenercenter;

import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.RequestListenerCenter;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.ExampleSFSZoneExtensionTest;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

public class RequestListenerCenterTest extends BaseTest {

    @Test
    public void testValidCase() {
        ExtensionConfiguration config = new ExtensionConfiguration();
        config.load(ExampleSFSZoneExtensionTest.class);
        
        RequestListenerCenter center = new RequestListenerCenter();
        center.addListeners(config.getRequestResponseClientClasses());
        
        List<RequestResponseClass> classes = center.getListeners("abc");
        assertEquals(classes.size(), 4);
        
        Set<String> commands = center.getCommands();
        assertEquals(1, commands.size());
        
        assertEquals(0, center.getListeners("a").size());
    }
    
}
