package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientRequestListener(command = "abc")
public class ExClientRequestListener {

    public void execute(AppContext context, PokerUser user) {
        
    }
    
}
