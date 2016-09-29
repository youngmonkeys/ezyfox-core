package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import com.google.common.collect.Sets;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.config.RoomEventHandlerCenter;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.structure.RoomHandlerClass;

public class RoomEventHandlerCenterTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testValidCase() {
        RoomEventHandlerCenter center = new RoomEventHandlerCenter();
        List<RoomHandlerClass> handlers = center.addHandlers(
                (Set)Sets.newHashSet(ClassA.class, ClassB.class), 
                (Set)Sets.newHashSet(ExampleRoom.class), 
                ExampleUser.class, new HashSet<Class<?>>());
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
