package com.tvd12.ezyfox.core.testing.command;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.command.AddCommand;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 * Created on Jun 14, 2016
 *
 */
public class AddCommandImplTest extends BaseCommandTest {

    @Test
    public void test() {
        context.command(AddCommand.class)
            .add(AppUser.class, AppUser.class);
        assertNotNull(context.command(AppUser.class));
    }
    
}
