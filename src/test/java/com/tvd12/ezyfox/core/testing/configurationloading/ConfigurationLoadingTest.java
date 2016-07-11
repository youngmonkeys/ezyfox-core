package com.tvd12.ezyfox.core.testing.configurationloading;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.annotation.AutoResponse;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.config.ConfigurationLoading;
import com.tvd12.ezyfox.core.constants.APIEvent;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

public class ConfigurationLoadingTest extends BaseTest {

    @Test(expectedExceptions = {RuntimeException.class})
    public void getConfigurationClassTest() {
        new ConfigurationLoading().load(ClassA.class);
    }
    @Test(expectedExceptions = {RuntimeException.class})
    public void getPackagesScanTest() {
        new ConfigurationLoading().load(ClassB.class);
    }
    
    @Test
    public void test() {
        ConfigurationLoading cf = new ConfigurationLoading();
        cf.load(ClassD.class);
        assertEquals(cf.getConfigClass(), ClassE.class);
        assertEquals(cf.getAutoResponseEvents().contains(APIEvent.ZONE_JOIN), true);
    }
    
    @PackagesScan(packages = {"com.tvd12.ezyfox.core.testing"})
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
