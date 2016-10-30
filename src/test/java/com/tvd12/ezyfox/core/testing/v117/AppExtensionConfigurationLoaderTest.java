    /**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.ContextConfiguration;
import com.tvd12.ezyfox.core.config.AbstractExtensionConfiguration;
import com.tvd12.ezyfox.core.config.AppExtensionConfiguration;
import com.tvd12.ezyfox.core.config.AppExtensionConfigurationImpl;
import com.tvd12.ezyfox.core.config.ComplexExtensionConfiguration;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.structure.RequestResponseClass;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117GameUser1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117MessageParams1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117ResponseParams1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117RoomConfig1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117GameUser4;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117RoomConfig4;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120ClientRequestListener1;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120GameUser1;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120MessageParams1;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120ObjectDeserializer1;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120ObjectSerializer1;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120ResponseParams1;
import com.tvd12.ezyfox.core.testing.v120.appconfig1.V120Room1;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120ClientRequestListener2;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120GameUser2;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120MessageParams2;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120ObjectDeserializer2;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120ObjectSerializer2;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120ResponseParams2;
import com.tvd12.ezyfox.core.testing.v120.appconfig2.V120Room2;

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
        assertNotNull(cfg.getUserAgentStruct().getUnwrapper());
        
        assertTrue(cfg.getResponseParamsStructs().size() > 0);
        assertTrue(cfg.getRoomAgentClasses().size() > 0);
        assertTrue(((AppExtensionConfigurationImpl)cfg).getRoomAgentStructs().size() > 0);

        assertEquals(((AbstractExtensionConfiguration)cfg).getRequestResponseClientClasses().size(), 11);
        
       
        assertNotNull(cfg.getGameUserAgentClass(V120GameUser1.class));
        assertNotNull(cfg.getRoomAgentClass(V120Room1.class));
        assertNotNull(cfg.getResponseParamsClass(V120ResponseParams1.class));
        
        ComplexExtensionConfiguration ex = (ComplexExtensionConfiguration)cfg;
        assertNotNull(getRequestResponseClass(
                ex.getRequestResponseClientStructs(), V120ClientRequestListener1.class));
        
        assertEquals(ex.getObjectDeserializerClasses().get(V120MessageParams1.class), V120ObjectDeserializer1.class);
        assertEquals(ex.getObjectSerializerClasses().get(V120MessageParams1.class), V120ObjectSerializer1.class);
        
        assertNotNull(cfg.getGameUserAgentClass(V120GameUser2.class));
        assertNotNull(cfg.getRoomAgentClass(V120Room2.class));
        assertNotNull(cfg.getResponseParamsClass(V120ResponseParams2.class));
        
        assertNotNull(getRequestResponseClass(
                ex.getRequestResponseClientStructs(), V120ClientRequestListener2.class));
        assertEquals(ex.getObjectDeserializerClasses().get(V120MessageParams2.class), V120ObjectDeserializer2.class);
        assertEquals(ex.getObjectSerializerClasses().get(V120MessageParams2.class), V120ObjectSerializer2.class);
    }
    
    private RequestResponseClass getRequestResponseClass(
            List<RequestResponseClass> structs, Class<?> clazz) {
        for(RequestResponseClass c : structs)
            if(c.getClazz() == clazz)
                return c;
        return null;
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
