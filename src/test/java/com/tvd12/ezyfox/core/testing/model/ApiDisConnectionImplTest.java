/**
 * 
 */
package com.tvd12.ezyfox.core.testing.model;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiDisconnectionImpl;
import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.model.ApiZone;
import com.tvd12.test.base.BaseTest;

/**
 * @author tavandung12
 *
 */
public class ApiDisConnectionImplTest extends BaseTest {

    @Test
    public void test() {
        ApiDisconnectionImpl disconnection = new ApiDisconnectionImpl();
        List<ApiRoom> joinedRooms = new ArrayList<>();
        ApiRoom room = new ApiRoom() {
        };
        ApiUser user = new ApiUser() {};
        ApiZone zone = mock(ApiZone.class);
        joinedRooms.add(room);
        Map<ApiRoom, Integer> ids = new HashMap<>();
        ids.put(room, 1);
        disconnection.setJoinedRooms(joinedRooms);
        disconnection.setPlayerIdsByRoom(ids);
        disconnection.setReason("UNKNOWN");
        disconnection.setUser(user);
        disconnection.setZone(zone);
        
        assertEquals(disconnection.joinedRooms().get(0), room);
        assertEquals(disconnection.playerIdsByRoom().get(room), new Integer(1));
        assertEquals(disconnection.reason(), "UNKNOWN");
        assertEquals(disconnection.user(), user);
        assertEquals(disconnection.zone(), zone);
    }
    
}
