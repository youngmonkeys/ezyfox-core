package com.tvd12.ezyfox.sfs2x.testing.extensionconfig;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientResponseHandler(command = "show")
@ClientRequestListener(command = "history")
public class ViewHistoryListener {

    public void execute(AppContext context, TestUser user) {

    }

}
