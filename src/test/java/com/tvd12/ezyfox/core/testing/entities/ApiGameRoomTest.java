package com.tvd12.ezyfox.core.testing.entities;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.command.RoomInfo;
import com.tvd12.ezyfox.core.constants.RoomRemoveMode;
import com.tvd12.ezyfox.core.entities.ApiGameRoom;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;

public class ApiGameRoomTest {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void test() {
        RoomInfo command = mock(RoomInfo.class);
        ExampleRoom room = new ExampleRoom();
        room.setCommand(command);
        room.setCapacity(10);
        room.setDynamic(true);
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
        
        when(command.isActive()).thenReturn(true);
        assertEquals(true, room.isActive());
        assertEquals(10, room.getCapacity());
        assertEquals(true, room.isDynamic());
        when(command.isEmpty()).thenReturn(false);
        assertEquals(false, room.isEmpty());
        when(command.isFull()).thenReturn(false);
        assertEquals(false, room.isFull());
        assertEquals(false, room.getCommand().isFull());
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
        
        when(command.getPlayersList(GameUser.class)).thenReturn((List)Lists.newArrayList(new GameUser()));
        when(command.getSpectatorsList(GameUser.class)).thenReturn((List)Lists.newArrayList(new GameUser(), new GameUser()));
        when(command.getUserList(GameUser.class)).thenReturn((List)Lists.newArrayList(new GameUser(), new GameUser(), new GameUser()));
        
        assertEquals(room.getPlayers().size(), 1);
        assertEquals(room.getSpectators().size(), 2);
        assertEquals(room.getUsers().size(), 3);
        
        room.switchPlayerToSpectator(new GameUser());
        room.switchSpectatorToPlayer(new GameUser());
        
        ExampleRoom1 exampleRoom1 = new ExampleRoom1();
        exampleRoom1.setOwner(null);
        assertNull(exampleRoom1.getOwner());
    }
    
    public static class ExampleUser extends ApiUser {
        public ExampleUser() {
            setName("dung");
        }
    }
    public static class ExampleRoom extends ApiGameRoom {

        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.core.entities.ApiGameRoom#userClass()
         */
        @Override
        protected Class<?> userClass() {
            // TODO Auto-generated method stub
            return GameUser.class;
        }
        
    }
    
    public static class ExampleRoom1 extends ApiGameRoom {

        /* (non-Javadoc)
         * @see com.tvd12.ezyfox.core.entities.ApiGameRoom#userClass()
         */
        @Override
        protected Class<?> userClass() {
            // TODO Auto-generated method stub
            return GameUser.class;
        }
        
    }
    
    public static class GameUser extends ApiGameUser {
        
    }
}
