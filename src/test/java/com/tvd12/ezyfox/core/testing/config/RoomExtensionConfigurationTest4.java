/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.testing.roomextensionconfig4.RoomConfig4;

/**
 * @author tavandung12
 *
 */
@RoomContextConfiguration(clazz = RoomConfig4.class)
public class RoomExtensionConfigurationTest4 {

    @Test(expectedExceptions = RuntimeException.class)
    public void test() {
        RoomExtensionConfiguration config = new RoomExtensionConfiguration();
        config.load(RoomExtensionConfigurationTest4.class);
    }
    
}
