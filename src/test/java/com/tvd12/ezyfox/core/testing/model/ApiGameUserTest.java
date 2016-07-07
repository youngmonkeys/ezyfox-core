package com.tvd12.ezyfox.core.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.UserInfo;
import com.tvd12.ezyfox.core.entities.ApiBuddyProperties;
import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;

import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class ApiGameUserTest {

    @Test
    public void test() {
        UserInfo command = mock(UserInfo.class);
        ExGameUser user = new ExGameUser();
        ApiUser parent = new ApiUser() {
            @Override
            public String getName() {
                return "Hello";
            }
            
        };
        ApiBuddyProperties bp = new ApiBuddyProperties();
        bp.setNickName("dungtv");
        parent.setCommand(command);
        parent.setBuddyProperties(bp);
        parent.setId(10);
        parent.setIp("127.0.0.1");
        
        user.setParent(parent);
        assertEquals(parent.getIp(), "127.0.0.1");
        assertEquals(user.getParent(), parent);
        assertEquals(user.getName(), "Hello");
        assertEquals(user.getId(), 10);
        assertEquals(user.getBuddyProperties().getNickName(), "dungtv");
        assertEquals(user.getIp(), "127.0.0.1");
        assertNotNull(user.getCommand());
    }
    
    public static class ExGameUser extends ApiGameUser {
        
    }
    
}
