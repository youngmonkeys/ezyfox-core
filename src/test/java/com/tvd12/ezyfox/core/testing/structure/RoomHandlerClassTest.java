package com.tvd12.ezyfox.core.testing.structure;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.RoomName;
import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.structure.RoomHandlerClass;

import static org.testng.Assert.*;

public class RoomHandlerClassTest {
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testValidCase() {
        RoomHandlerClass handler = new RoomHandlerClass(
                ExampleRoomHandler1.class, 
                (List)Lists.newArrayList(ExampleRoom.class),
                ExampleUser.class,
                new ArrayList<Class<?>>());
        assertEquals("abc", handler.getRoomName());
        assertEquals(handler.getUserClass(), ExampleUser.class);
        assertEquals(handler.getRoomClass(), ExampleRoom.class);
        
        handler = new RoomHandlerClass(
                ExampleRoomHandler2.class, 
                (List)Lists.newArrayList(ExampleRoom.class), 
                ExampleUser.class,
                new ArrayList<Class<?>>());
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
