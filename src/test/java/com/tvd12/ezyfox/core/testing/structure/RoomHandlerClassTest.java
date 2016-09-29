package com.tvd12.ezyfox.core.testing.structure;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.google.common.collect.Sets;
import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.RoomHandlerClass;

public class RoomHandlerClassTest {
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testValidCase() {
        RoomHandlerClass handler = new RoomHandlerClass(
                ExampleRoomHandler1.class, 
                (Set)Sets.newHashSet(ExampleRoom.class),
                ExampleUser.class,
                new HashSet<Class<?>>());
        assertEquals("abc", handler.getRoomName());
        assertEquals(handler.getUserClass(), ExampleUser.class);
        assertEquals(handler.getRoomClass(), ExampleRoom.class);
        
        handler = new RoomHandlerClass(
                ExampleRoomHandler2.class, 
                (Set)Sets.newHashSet(ExampleRoom.class), 
                ExampleUser.class,
                new HashSet<Class<?>>());
        assertEquals("", handler.getRoomName());
        
    }

    public static class ExampleUser {
        
    }
    
    public static class ExampleRoom {
        
    }
    
    @RoomName("abc")
    @ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
    public static class ExampleRoomHandler1 {
        public void handle(AppContext context, ExampleRoom room, ExampleUser user) {
            
        }
    }
    
    @ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
    public static class ExampleRoomHandler2 {
        public void handle(AppContext context, ExampleRoom room, ExampleUser user) {
            
        }
    }
    
}
