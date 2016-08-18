/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.RoomContextConfiguration;
import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;
import com.tvd12.ezyfox.core.testing.roomextensionconfig3.RoomConfig3;

/**
 * @author tavandung12
 *
 */
@RoomContextConfiguration(clazz = RoomConfig3.class)
public class RoomExtensionConfigurationTest3 {

    @Test
    public void test() {
        RoomExtensionConfiguration config = new RoomExtensionConfiguration();
        config.load(RoomExtensionConfigurationTest3.class);
    }
    
}
