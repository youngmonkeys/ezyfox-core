package com.tvd12.ezyfox.core.testing.utilities;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.model.ApiGameUser;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.util.UserAgentUtil;
import com.tvd12.test.base.BaseTest;

import static org.testng.Assert.*;

public class UserAgentUtilTest extends BaseTest {
    
    @Test
    public void testValidCase() {
        ExUser user = new ExUser();
        VideoPokerUser videoPokerUser = new VideoPokerUser();
        user.addChild(new PokerUser());
        user.addChild(videoPokerUser);
        
        ApiGameUser gameUser = UserAgentUtil.getGameUser(user, VideoPokerUser.class);
        
        assertEquals(gameUser, videoPokerUser);
        user = new ExUser();
        user.addChild(new PokerUser());
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
