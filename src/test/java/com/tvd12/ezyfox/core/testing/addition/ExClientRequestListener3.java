package com.tvd12.ezyfox.core.testing.addition;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;
import com.tvd12.ezyfox.core.testing.extensionconfiguration.PokerUser;

@ClientRequestListener(command = "abc")
public class ExClientRequestListener3 {

    public void execute(AppContext context, PokerUser user) {
        
    }
    
}
