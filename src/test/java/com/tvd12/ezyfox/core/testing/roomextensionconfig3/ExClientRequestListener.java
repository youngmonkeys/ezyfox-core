package com.tvd12.ezyfox.core.testing.roomextensionconfig3;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ExecuteMethod;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.testing.config.ZoneRoomHandlerCenterTest.ExRoom;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.PokerUser;

@ClientRequestListener(command = "abc")
public class ExClientRequestListener {

    @ExecuteMethod
    public void execute1(AppContext context, ExRoom room, PokerUser user) {
        
    }
    
}
