package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.AdditionalServerEventHandlers;
import com.tvd12.ezyfox.core.annotation.PackagesScan;

@PackagesScan(packages = {"com.tvd12.ezyfox.core.testing.extensionconfiguration"})
@AdditionalClientRequestListeners(classes = {ExClientRequestListener.class})
@AdditionalServerEventHandlers(classes = {ExServereEventHandler.class})
public class TestAppConfig {

}
