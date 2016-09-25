package com.tvd12.ezyfox.core.testing.context;

import com.tvd12.ezyfox.core.annotation.AutoResponse;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.annotation.RoomPackages;
import com.tvd12.ezyfox.core.constants.APIEvent;

@PackagesScan(packages = {"com.tvd12.ezyfox.core.testing.context"})
@RoomPackages(packages = {
        "com.tvd12.ezyfox.core.testing.v117.roomconfig1",
        "com.tvd12.ezyfox.core.testing.roomcontext"
        })
@AutoResponse(events = {APIEvent.ZONE_JOIN})
public class AppConfig {

}
