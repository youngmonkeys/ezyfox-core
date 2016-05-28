package com.tvd12.ezyfox.core.testing.configurationloading;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.annotation.AppContextConfiguration;
import com.tvd12.ezyfox.core.config.ConfigurationLoading;
import com.tvd12.test.base.BaseTest;

public class ConfigurationLoadingTest extends BaseTest {

    @Test(expectedExceptions = {RuntimeException.class})
    public void getConfigurationClassTest() {
        new ConfigurationLoading().load(ClassA.class);
    }
    @Test(expectedExceptions = {RuntimeException.class})
    public void getPackagesScanTest() {
        new ConfigurationLoading().load(ClassB.class);
    }
    
    public static class ClassA {
        
    }
    
    @AppContextConfiguration(clazz = ClassC.class)
    public static class ClassB {
        
    }
    
    
    public static class ClassC {
        
    }
    
}
