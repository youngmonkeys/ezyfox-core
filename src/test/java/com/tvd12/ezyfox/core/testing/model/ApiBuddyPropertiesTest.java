/**
 * 
 */
package com.tvd12.ezyfox.core.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiBuddyProperties;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class ApiBuddyPropertiesTest extends BaseTest {

    @Test
    public void test() {
        ApiBuddyProperties pro = new ApiBuddyProperties();
        pro.setInited(true);
        pro.setNickName("dungtv");
        pro.setOnline(true);
        pro.setProperty("name", "hello");
        pro.setState("off");
        
        assertEquals(pro.getNickName(), "dungtv");
        assertEquals(pro.getProperty("name"), "hello");
        assertEquals(pro.getState(), "off");
        assertEquals(pro.isInited(), true);
        assertEquals(pro.isOnline(), true);
    }

}
