/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.config.AppExtensionConfiguration;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117GameUser1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117RoomEntryPoint1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117GameUser4;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117RoomEntryPoint4;

/**
 * @author tavandung12
 *
 */
@AppContextConfiguration(clazz = V117AppConfig1.class)
public class AppExtensionConfigurationLoaderTest {
    
    @Test
    public void test() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(V117EntryPoint2.class);
        AppExtensionConfiguration cfg = loader.load();
        assertNotNull(cfg.getGameUserAgentClass(V117GameUser1.class));
        assertNotNull(cfg.getGameUserAgentClass(V117GameUser4.class));
        assertNotNull(cfg.getRoomExtensionConfiguration(V117RoomEntryPoint1.class));
        assertNotNull(cfg.getRoomExtensionConfiguration(V117RoomEntryPoint4.class));
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void findUserClassTestInvalidCase() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(getClass());
        loader.load();
    }
    
}
