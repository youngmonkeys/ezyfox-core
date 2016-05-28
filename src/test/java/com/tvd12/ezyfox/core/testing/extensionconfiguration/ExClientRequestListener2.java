package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientRequestListener(command = "abc")
public class ExClientRequestListener2 {

    public void execute(AppContext context, ExampleUser user) {
        
    }
    
}
