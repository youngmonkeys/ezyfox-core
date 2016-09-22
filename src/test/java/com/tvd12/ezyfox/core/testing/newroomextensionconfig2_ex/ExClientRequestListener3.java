package com.tvd12.ezyfox.core.testing.newroomextensionconfig2_ex;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.testing.config.ZoneRoomHandlerCenterTest.ExRoom;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.PokerUser;

@ClientRequestListener(command = "abc")
@ParamsMapper(deserializer = ClientRequestDeserializer2.class)
public class ExClientRequestListener3 {

    public void execute(AppContext context, ExRoom room, PokerUser user) {
        
    }
    
}
