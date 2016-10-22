package com.tvd12.ezyfox.core.testing.utilities;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiUser;
import com.tvd12.ezyfox.core.util.UserAgentUtil;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

public class UserAgentUtilTest extends BaseTest {
    
    @Test
    public void testValidCase() {
        ExUser user = new ExUser();
        user.setName("user");
        VideoPokerUser videoPokerUser = new VideoPokerUser();
        PokerUser pokerUser = new PokerUser();
        pokerUser.setParent(user);
        videoPokerUser.setParent(user);
        user.addChild(pokerUser);
        user.addChild(videoPokerUser);
        
        ApiGameUser gameUser = UserAgentUtil.getGameUser(user, VideoPokerUser.class);
        
        assertEquals(gameUser, videoPokerUser);
        user = new ExUser();
        user.setName("user");
        PokerUser guser = new PokerUser();
        guser.setParent(user);
        user.addChild(guser);
        gameUser = UserAgentUtil.getGameUser(user, VideoPokerUser.class);
        assertNull(gameUser);
    }
    
    @Override
    public Class<?> getTestClass() {
        return UserAgentUtil.class;
    }
    
    public static class ExUser extends ApiUser {
        
    }
    
    public static class PokerUser extends ApiGameUser {
        
    }
    
    public static class VideoPokerUser extends ApiGameUser {
        
    }
}
