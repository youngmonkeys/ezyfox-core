/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.roomextensionconfig4.RoomConfig4;

/**
 * @author tavandung12
 *
 */
@RoomContextConfiguration(clazz = RoomConfig4.class)
public class RoomExtensionConfigurationTest4 {

    @Test(expectedExceptions = IllegalStateException.class)
    public void test() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setEntryPoint(RoomExtensionConfigurationTest4.class);
        loader.load();
    }
    
}
