/**
 * 
 */
package com.tvd12.ezyfox.core.testing.roomextensionconfig2;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.constants.ServerEvent;
import com.tvd12.ezyfox.core.content.AppContext;

/**
 * @author tavandung12
 *
 */
@ServerEventHandler(event = ServerEvent.SERVER_READY)
public class ServerReadyHandler {

    public void handle(AppContext context) {
        
    }
    
}
