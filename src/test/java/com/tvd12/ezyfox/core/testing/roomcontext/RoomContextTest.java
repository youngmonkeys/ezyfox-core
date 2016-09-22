/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomcontext;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.content.impl.BaseRoomContext;
import com.tvd12.ezyfox.core.testing.context.AppEntryPoint;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import com.tvd12.ezyfox.core.testing.context.ExAppContextImpl;
import com.tvd12.ezyfox.core.testing.context.PokerUser;
import com.tvd12.ezyfox.core.testing.context.UserDeserializer;
import com.tvd12.ezyfox.core.testing.context.UserSerializer;
import com.tvd12.test.reflect.MethodInvoker;

import static org.testng.Assert.*;
/**
 * @author tavandung12
 *
 */
public class RoomContextTest {
    
    @Test
    public void test() {
         BaseAppContext context = newAppContext();
         BaseRoomContext roomContext = new BaseRoomContext();
         roomContext.setAppContext(context);
         roomContext.initialize(ExRoomExtension.class);
         assertNotNull(roomContext.getObjectDeserializer(CurrentState.class));
         assertNotNull(roomContext.getObjectSerializer(CurrentState.class));
         assertNotNull(roomContext.command(AddCommand.class));
         roomContext.set("a", "b");
         assertEquals(roomContext.get("a", String.class), "b");
         assertNotNull(MethodInvoker.create()
                 .object(roomContext)
                 .method("getCommandConstructor")
                 .param(CommandA.class)
                 .invoke());
         assertNotNull(MethodInvoker.create()
                 .object(roomContext)
                 .method("getCommand")
                 .param(PokerUser.class)
                 .invoke());
         assertEquals(roomContext.getAppContext(), context);
         
         assertEquals(roomContext.getUserAgentClass(), context.getUserAgentClass());
         assertEquals(roomContext.getGameUserAgentClasses(), context.getGameUserAgentClasses());
         assertEquals(roomContext.getRoomAgentClasses(), context.getRoomAgentClasses());
    }

    private BaseAppContext newAppContext() {
        BaseAppContext answer = new ExAppContextImpl();
        answer.initialize(AppEntryPoint.class);
        answer.addObjectSerializer(AppUser.class, new UserSerializer());
        answer.addObjectDeserializer(AppUser.class, new UserDeserializer());
        return answer;
    }
    
    public static class CommandA {
        
    }
    
}
