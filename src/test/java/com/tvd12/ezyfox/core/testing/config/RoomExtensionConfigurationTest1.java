/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.RoomExtensionConfiguration;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationTest1 {

    @Test(expectedExceptions = RuntimeException.class)
    public void test() {
        RoomExtensionConfiguration config = new RoomExtensionConfiguration();
        config.load(RoomExtensionConfigurationTest1.class);
    }
    
}
