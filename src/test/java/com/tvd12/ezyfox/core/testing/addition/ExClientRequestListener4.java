package com.tvd12.ezyfox.core.testing.addition;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.testing.config.ZoneRoomHandlerCenterTest.ExRoom;
import com.tvd12.ezyfox.core.testing.context.AppUser;

@ClientRequestListener(command = "abc")
public class ExClientRequestListener4 {

    public void execute(AppContext context, ExRoom room, AppUser user) {
        
    }
    
}
