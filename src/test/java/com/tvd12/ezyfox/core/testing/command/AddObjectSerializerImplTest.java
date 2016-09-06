/**
 * 
 */
package com.tvd12.ezyfox.core.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.AddObjectSerializer;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import com.tvd12.ezyfox.core.testing.context.UserSerializer;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class AddObjectSerializerImplTest extends BaseCommandTest {

    @Test
    public void test() {
        context.command(AddObjectSerializer.class)
            .add(AppUser.class, new UserSerializer());
        assertNotNull(context.getObjectSerializer(AppUser.class));
    }
    
}
