/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.roomextensionconfig3.RoomConfig3;

/**
 * @author tavandung12
 *
 */
@ContextConfiguration(clazz = RoomConfig3.class)
public class RoomExtensionConfigurationTest3 {

    @Test
    public void test() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setConfigClass(RoomConfig3.class);
        loader.load();
    }
    
}
