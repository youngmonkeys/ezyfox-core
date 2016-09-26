/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.roomextensionconfig1.RoomConfig1;

/**
 * @author tavandung12
 *
 */
@ContextConfiguration(clazz = RoomConfig1.class)
public class RoomExtensionConfigurationTest2 {

    @Test(expectedExceptions = RuntimeException.class)
    public void test() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setConfigClass(RoomConfig1.class);
        loader.load();
    }
    
}
