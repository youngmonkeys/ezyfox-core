package com.tvd12.ezyfox.core.testing.apiroom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.model.RoomRemoveMode;

public class ApiRoomTest {

    @Test
    public void test() {
        ApiRoom room = new ExampleRoom();
        room.setActive(true);
        room.setCapacity(10);
        room.setDynamic(true);
        room.setEmpty(false);
        room.setFull(false);
        room.setGame(true);
        room.setGroupdId("123");
        room.setHidden(false);
        room.setId(123);
        room.setMaxRoomVariablesAllowed(30);
        room.setMaxSpectators(30);
        room.setMaxUsers(30);
        room.setName("Lobby");
        room.setOwner(new ExampleUser());
        room.setPassword("123");
        room.setProperty("hello", "world");
        room.setPasswordProtected(true);
        room.setPublic(true);
        room.setUseWordsFilter(false);
        room.setVariablesCount(30);
        
        assertEquals(true, room.isActive());
        assertEquals(10, room.getCapacity());
        assertEquals(true, room.isDynamic());
        assertEquals(false, room.isEmpty());
        assertEquals(false, room.isFull());
        assertEquals(true, room.isGame());
        assertEquals("123", room.getGroupdId());
        assertEquals(false, room.isHidden());
        assertEquals(123, room.getId());
        assertEquals(30, room.getMaxRoomVariablesAllowed());
        assertEquals(30, room.getMaxSpectators());
        assertEquals(30, room.getMaxUsers());
        assertEquals("Lobby", room.getName());
        assertEquals("dung", ((ExampleUser)room.getOwner()).getName());
        assertEquals("123", room.getPassword());
        assertEquals("world", room.getProperty("hello"));
        assertEquals(room.getProperty("hello", String.class), "world");
        assertEquals(true, room.isPasswordProtected());
        assertEquals(true, room.isPublic());
        assertEquals(false, room.isUseWordsFilter());
        assertEquals(room.getRemoveMode(), RoomRemoveMode.NEVER_REMOVE);
        assertEquals(30, room.getVariablesCount());
        assertEquals(1, room.getProperties().size());
        room.removeProperty("hello");
        assertNull(room.getProperty("hello"));
        room.setRemoveMode(RoomRemoveMode.DEFAULT);
        assertEquals(room.getRemoveMode(), RoomRemoveMode.DEFAULT);
        
        ExampleRoom1 exampleRoom1 = new ExampleRoom1();
        exampleRoom1.setOwner(null);
        assertNull(exampleRoom1.getOwner());
    }
    
    public static class ExampleUser extends ApiUser {
        public ExampleUser() {
            setName("dung");
        }
    }
    public static class ExampleRoom extends ApiRoom {
        
        private ExampleUser owner;
        
        @Override
        public void setOwner(Object owner) {
            this.owner = (ExampleUser)owner;
        }

        @SuppressWarnings("unchecked")
        @Override
        public ExampleUser getOwner() {
            return owner;
        }
        
    }
    
    public static class ExampleRoom1 extends ApiRoom {
        
    }
}
