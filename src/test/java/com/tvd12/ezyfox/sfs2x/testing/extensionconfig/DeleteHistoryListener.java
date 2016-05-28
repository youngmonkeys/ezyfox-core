package com.tvd12.ezyfox.sfs2x.testing.extensionconfig;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientRequestListener(command = "delete-history")
public class DeleteHistoryListener {

    public void execute(AppContext context, TestUser user) {
        
    }
    
}
