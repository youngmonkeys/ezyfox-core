    /**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.core.config.AppExtensionConfiguration;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117GameUser1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117MessageParams1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117ResponseParams1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117RoomConfig1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117GameUser4;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117RoomConfig4;

/**
 * @author tavandung12
 *
 */
@ContextConfiguration(clazz = V117AppConfig1.class)
public class AppExtensionConfigurationLoaderTest {
    
    @Test
    public void test() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(V117EntryPoint2.class);
        AppExtensionConfiguration cfg = loader.load();
        assertNotNull(cfg.getGameUserAgentClass(V117GameUser1.class));
        assertNotNull(cfg.getGameUserAgentClass(V117GameUser4.class));
        assertNotNull(cfg.getRoomExtensionConfiguration(V117RoomConfig1.class));
        assertNotNull(cfg.getRoomExtensionConfiguration(V117RoomConfig4.class));
        assertNotNull(cfg.getResponseParamsClass(V117ResponseParams1.class));
        assertNotNull(cfg.getMessageParamsClass(V117MessageParams1.class));
        assertNotNull(cfg.getUserAgentClass().getUnwrapper());
    }

    @Test(expectedExceptions = {IllegalStateException.class})
    public void findUserClassTestInvalidCase() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(getClass());
        loader.load();
    }
    
    @Test(expectedExceptions = {IllegalStateException.class})
    public void checkAgentClassTestInvalidCase() {
        AppExtensionConfigurationLoader loader = new AppExtensionConfigurationLoader();
        loader.setEntryPoint(V117EntryPoint3.class);
        loader.load();
    }
    
}
