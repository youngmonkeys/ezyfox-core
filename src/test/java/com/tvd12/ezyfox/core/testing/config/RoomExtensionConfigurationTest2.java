/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.testing.roomextensionconfig1.RoomConfig1;

/**
 * @author tavandung12
 *
 */
@RoomContextConfiguration(clazz = RoomConfig1.class)
public class RoomExtensionConfigurationTest2 {

    @Test(expectedExceptions = RuntimeException.class)
    public void test() {
        RoomExtensionConfiguration config = new RoomExtensionConfiguration();
        config.load(RoomExtensionConfigurationTest2.class);
    }
    
}
