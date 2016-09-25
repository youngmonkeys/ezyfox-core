/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomcontext;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.content.impl.BaseAppContext;
import com.tvd12.ezyfox.core.content.impl.BaseRoomContext;
import com.tvd12.ezyfox.core.testing.context.AppEntryPoint;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import com.tvd12.ezyfox.core.testing.context.ExAppContextImpl;
import com.tvd12.ezyfox.core.testing.context.UserDeserializer;
import com.tvd12.ezyfox.core.testing.context.UserSerializer;
/**
 * @author tavandung12
 *
 */
public class RoomContextTest {
    
    @Test
    public void test() {
         BaseAppContext context = newAppContext();
         BaseRoomContext roomContext = new BaseRoomContext();
         roomContext.init(ExRoomExtension.class, context);
         assertNotNull(roomContext.getObjectDeserializer(CurrentState.class));
         assertNotNull(roomContext.getObjectSerializer(CurrentState.class));
         assertNotNull(roomContext.command(AddCommand.class));
         roomContext.set("a", "b");
         assertEquals(roomContext.get("a", String.class), "b");
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
