/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationTest1 {

    @Test(expectedExceptions = RuntimeException.class)
    public void test() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setConfigClass(Class.class);
        loader.load();
    }
    
}
