package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.AdditionalServerEventHandlers;
import com.tvd12.ezyfox.core.annotation.PackagesScan;

@PackagesScan(packages = {"com.tvd12.ezyfox.core.testing.extensionconfiguration"})
@AdditionalClientRequestListeners(
        value = {"com.tvd12.ezyfox.core.testing.extensionconfiguration.ExClientRequestListener"}, 
        classes = {ExClientRequestListener2.class})
@AdditionalServerEventHandlers(
        value = {"com.tvd12.ezyfox.core.testing.extensionconfiguration.ExServereEventHandler"},
        classes = {ExServereEventHandler2.class})
//@AdditionalClientRequestListeners({"com.tvd12.ezyfox.core.testing.extensionconfiguration.ExClientRequestListener"})
//@AdditionalServerEventHandlers({"com.tvd12.ezyfox.core.testing.extensionconfiguration.ExServereEventHandler"})
public class TestAppConfig {

}
