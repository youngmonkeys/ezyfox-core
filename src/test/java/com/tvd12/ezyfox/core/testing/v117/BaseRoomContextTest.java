/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.content.impl.BaseRoomContext;
import com.tvd12.ezyfox.core.testing.v117.BaseAppContextTest.AppContext1Impl;
import com.tvd12.ezyfox.core.testing.v117.roomconfig1.V117RoomEntryPoint1;
import com.tvd12.ezyfox.core.testing.v117.roomconfig4.V117ResponseParams4;

/**
 * @author tavandung12
 *
 */
public class BaseRoomContextTest {
    
    @Test
    public void test() {
        BaseAppContext context = new AppContext1Impl();
        context.initialize(V117EntryPoint2.class);
        BaseRoomContext roomContext = new RoomContext1Impl();
        roomContext.init(context, V117RoomEntryPoint1.class);
        assertNotNull(roomContext.getResponseParamsClass(V117ResponseParams4.class));
        assertTrue(roomContext.getClientRequestListeners("v117_1").size() == 1);
        assertTrue(context.getGameUserAgentClasses().size() >= 2);
    }

    
    public static class RoomContext1Impl extends BaseRoomContext {

    }
}
