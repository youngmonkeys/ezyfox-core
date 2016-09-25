/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.v117.roomconfig3.V117RoomEntryPoint3;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationLoaderTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void findRoomClassTestInvalidCase() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setEntryPoint(V117RoomEntryPoint3.class);
        loader.load();
    }
    
}
