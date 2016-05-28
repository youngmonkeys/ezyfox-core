package com.tvd12.ezyfox.core.testing.factory;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.UserAgent;
import com.tvd12.ezyfox.core.factory.UserAgentFactory;
import com.tvd12.ezyfox.core.model.ApiGameUser;
import com.tvd12.ezyfox.core.model.ApiUser;
import com.tvd12.ezyfox.core.structure.UserAgentClass;
import com.tvd12.test.base.BaseTest;

public class UserAgentFactoryTest extends BaseTest {

    @Test
    public void test() {
        UserAgentClass userAgentClass = new UserAgentClass(ExUser.class);
        UserAgentClass pokerUserAgentClass = new UserAgentClass(PokerUser.class);
        UserAgentClass videoPokerAgentClass = new UserAgentClass(VideoPokerUser.class);
        
        ApiUser apiUser = UserAgentFactory.newUserAgent(
                "Hello",
                userAgentClass, 
                Lists.newArrayList(pokerUserAgentClass, videoPokerAgentClass));
        assertEquals(apiUser.getName(), "Hello");
        assertEquals(apiUser.getChildren().size(), 2);
    }
    
    
    @Override
    public Class<?> getTestClass() {
        return UserAgentFactory.class;
    }
    
    @UserAgent
    public static class ExUser extends ApiUser {
        
    }
    
    @GameUser
    public static class PokerUser extends ApiGameUser {
        
    }
    
    @GameUser
    public static class VideoPokerUser extends ApiGameUser {
        
    }
    
}
