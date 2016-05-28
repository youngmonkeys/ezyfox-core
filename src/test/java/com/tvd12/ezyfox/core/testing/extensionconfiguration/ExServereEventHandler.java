package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.ServerEventHandler;
import com.tvd12.ezyfox.core.content.AppContext;

@ServerEventHandler(event = "login")
public class ExServereEventHandler {

    public void handle(AppContext context) {}
    
}
