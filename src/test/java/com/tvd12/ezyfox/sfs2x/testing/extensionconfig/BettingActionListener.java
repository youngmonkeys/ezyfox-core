package com.tvd12.ezyfox.sfs2x.testing.extensionconfig;

import com.tvd12.ezyfox.core.annotation.ClientRequestListener;
import com.tvd12.ezyfox.core.annotation.ClientResponseHandler;
import com.tvd12.ezyfox.core.content.AppContext;

@ClientRequestListener(command = "betting")
@ClientResponseHandler(command = "betting")
public class BettingActionListener {

    public void execute(AppContext context, TestUser user) {

    }

}
