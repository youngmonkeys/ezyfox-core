package com.tvd12.ezyfox.core.testing.model;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiBuddyProperties;
import com.tvd12.ezyfox.core.model.ApiGameUser;
import com.tvd12.ezyfox.core.model.ApiUser;

import static org.testng.Assert.*;

public class ApiGameUserTest {

    @Test
    public void test() {
        ExGameUser user = new ExGameUser();
        ApiUser parent = new ApiUser() {
            @Override
            public String getName() {
                return "Hello";
            }
            
        };
        ApiBuddyProperties bp = new ApiBuddyProperties();
        bp.setNickName("dungtv");
        parent.setBuddyProperties(bp);
        parent.setId(10);
        
        user.setParent(parent);
        assertEquals(user.getParent(), parent);
        assertEquals(user.getName(), "Hello");
        assertEquals(user.getId(), 10);
        assertEquals(user.getBuddyProperties().getNickName(), "dungtv");
    }
    
    public static class ExGameUser extends ApiGameUser {
        
    }
    
}
