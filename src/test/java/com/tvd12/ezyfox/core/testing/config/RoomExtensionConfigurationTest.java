/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.config.ExtensionConfiguration;
import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.roomextensionconfig2.RoomConfig2;

/**
 * @author tavandung12
 *
 */
@RoomContextConfiguration(clazz = RoomConfig2.class)
public class RoomExtensionConfigurationTest {

    @Test
    public void test() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setEntryPoint(RoomExtensionConfigurationTest.class);
        ExtensionConfiguration config = loader.load();
        assertEquals(config.getRequestResponseClientClasses().size(), 3);
        assertEquals(config.getServerEventHandlerClasses().size(), 2);
        assertEquals(config.getObjectDeserializerClasses().size(), 3);
    }
    
    
}
