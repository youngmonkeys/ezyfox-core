/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.loader.RoomExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.v117.roomconfig3.V117RoomConfig3;
import com.tvd12.ezyfox.core.testing.v117.roomconfig5.V117RoomConfig5;

/**
 * @author tavandung12
 *
 */
public class RoomExtensionConfigurationLoaderTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void findGameUserClassTestInvalidCase() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setConfigClass(V117RoomConfig3.class);
        loader.load();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void findRoomClassTestInvalidCase() {
        RoomExtensionConfigurationLoader loader = new RoomExtensionConfigurationLoader();
        loader.setConfigClass(V117RoomConfig5.class);
        loader.load();
    }
    
}
