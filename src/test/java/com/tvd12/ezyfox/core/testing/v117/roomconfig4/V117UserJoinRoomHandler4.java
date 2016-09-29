/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117.roomconfig4;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.USER_JOIN_ROOM)
public class V117UserJoinRoomHandler4 {

    public void handle(AppContext context, V117Room4 room, V117GameUser4 user) {
        
    }
    
}
