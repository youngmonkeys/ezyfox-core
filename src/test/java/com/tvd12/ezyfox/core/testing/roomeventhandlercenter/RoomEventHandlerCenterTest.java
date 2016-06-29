package com.tvd12.ezyfox.core.testing.roomeventhandlercenter;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.RoomEventHandlerCenter;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.structure.RoomHandlerClass;

public class RoomEventHandlerCenterTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testValidCase() {
        RoomEventHandlerCenter center = new RoomEventHandlerCenter();
        List<RoomHandlerClass> handlers = center.addHandlers(
                (List)Lists.newArrayList(ClassA.class, ClassB.class), 
                (List)Lists.newArrayList(ExampleRoom.class), 
                ExampleUser.class, new ArrayList<Class<?>>());
        assertEquals(2, handlers.size());
        assertEquals(2, center.getHandlers().size());
        assertEquals(1, handlers.get(0).getPriority());
    }

    public static class ExampleUser extends ApiUser {
        
    }
    
    public static class ExampleRoom extends ApiRoom {

    }
    
    @ServerEventHandler(event="USER_JOIN_ROOM", priority = 2)
    public static class ClassA {
        public void handle(AppContext context, ExampleRoom room, ExampleUser user) {
            
        }
    }
    
    @ServerEventHandler(event="USER_JOIN_ROOM", priority = 1)
    public static class ClassB {
        public void handle(AppContext context, ExampleRoom room, ExampleUser user) {
            
        }
    }
    
}
