package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.AdditionalClientRequestListeners;
import com.tvd12.ezyfox.core.annotation.AdditionalServerEventHandlers;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.annotation.RoomPackages;

@PackagesScan(packages = {"com.tvd12.ezyfox.core.testing.extensionconfiguration"})
@AdditionalClientRequestListeners(
        value = {"com.tvd12.ezyfox.core.testing.addition.ExClientRequestListener3"}, 
        classes = {ExClientRequestListener2.class})
@AdditionalServerEventHandlers(
        value = {"com.tvd12.ezyfox.core.testing.addition.ExServereEventHandler3"},
        classes = {ExServereEventHandler2.class})
@RoomPackages(packages = {"com.tvd12.ezyfox.core.testing.extensionconfiguration"})
public class TestAppConfig {

}
