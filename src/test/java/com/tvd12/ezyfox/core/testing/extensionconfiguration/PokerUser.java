package com.tvd12.ezyfox.core.testing.extensionconfiguration;

import com.tvd12.ezyfox.core.annotation.GameUser;
import com.tvd12.ezyfox.core.annotation.ParamsMapper;
import com.tvd12.ezyfox.core.entities.ApiGameUser;

@GameUser
@ParamsMapper(serializer = PokerUserSerializer.class)
public class PokerUser extends ApiGameUser {
    
}