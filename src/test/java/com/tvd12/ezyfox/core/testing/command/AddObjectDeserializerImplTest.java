/**
 * 
 */
package com.tvd12.ezyfox.core.testing.command;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.AddObjectDeserializer;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import com.tvd12.ezyfox.core.testing.context.UserDeserializer;

/**
 * @author tavandung12
 *
 */
public class AddObjectDeserializerImplTest extends BaseCommandTest {

    @Test
    public void test() {
        context.command(AddObjectDeserializer.class)
            .add(AppUser.class, new UserDeserializer());
        assertNotNull(context.getObjectDeserializer(AppUser.class));
    }
    
}
