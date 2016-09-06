package com.tvd12.ezyfox.core.testing.context;

import com.tvd12.ezyfox.core.annotation.AutoResponse;
import com.tvd12.ezyfox.core.annotation.PackagesScan;
import com.tvd12.ezyfox.core.constants.APIEvent;

@PackagesScan(packages = {"com.tvd12.ezyfox.core.testing.context"})
@AutoResponse(events = {APIEvent.ZONE_JOIN})
public class AppConfig {

}
