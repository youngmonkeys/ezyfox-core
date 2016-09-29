/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.v117.roomconfig2.V117RoomEntryPoint2;

/**
 * @author tavandung12
 *
 */
public class ConfigurationLoaderTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkAgentClassTestInvalidCase() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setConfigClass(V117RoomEntryPoint2.class);
        loader.load();
    }
    
}
