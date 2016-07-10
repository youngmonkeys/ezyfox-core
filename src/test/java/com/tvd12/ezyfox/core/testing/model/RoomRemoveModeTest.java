/**
 * 
 */
package com.tvd12.ezyfox.core.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.constants.RoomRemoveMode;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class RoomRemoveModeTest extends BaseTest {

    @Test
    public void test() {
        assertEquals(RoomRemoveMode.valueOf("DEFAULT"), RoomRemoveMode.DEFAULT);
    }
    
    
}
