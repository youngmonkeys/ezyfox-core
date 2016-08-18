package com.tvd12.ezyfox.core.testing.roomextensionconfig2;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.testing.config.ZoneRoomHandlerCenterTest.ExRoom;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.PokerUser;

@ClientRequestListener(command = "abc")
public class ExClientRequestListener {

    public void execute(AppContext context, ExRoom room, PokerUser user) {
        
    }
    
}
