package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.annotation.AutoResponse;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.annotation.RoomPackages;
import com.tvd12.ezyfox.core.config.AppExtensionConfiguration;
import com.tvd12.ezyfox.core.config.loader.AppExtensionConfigurationLoader;
import com.tvd12.ezyfox.core.constants.APIEvent;
import com.tvd12.test.base.BaseTest;

public class ConfigurationLoadingTest extends BaseTest {

    @Test(expectedExceptions = {IllegalStateException.class})
    public void getConfigurationClassTest() {
        AppExtensionConfigurationLoader loader = 
                new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ClassA.class);
        loader.load();
    }
    @Test(expectedExceptions = {IllegalStateException.class})
    public void getPackagesScanTest() {
        AppExtensionConfigurationLoader loader = 
                new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ClassB.class);
        loader.load();
    }
    
    @Test
    public void test() {
        AppExtensionConfigurationLoader loader = 
                new AppExtensionConfigurationLoader();
        loader.setEntryPoint(ClassD.class);
        AppExtensionConfiguration cf = loader.load();
        assertEquals(cf.isAutoResponseEvent(APIEvent.ZONE_JOIN), true);
    }
    
    @PackagesScan(packages = {"com.tvd12.ezyfox.core.testing.context"})
    @RoomPackages(packages = {"com.tvd12.ezyfox.core.testing.v117.roomconfig1"})
    @AutoResponse(events = {APIEvent.ZONE_JOIN})
    public static class ClassE {
        
    }

    @AppContextConfiguration(clazz = ClassE.class)
    public static class ClassD {
        
    }
    
    public static class ClassA {
        
    }
    
    @AppContextConfiguration(clazz = ClassC.class)
    public static class ClassB {
        
    }
    
    
    public static class ClassC {
        
    }
    
}
