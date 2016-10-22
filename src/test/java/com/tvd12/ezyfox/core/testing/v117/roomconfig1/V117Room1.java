/**
 * 
 */
package com.tvd12.ezyfox.core.testing.v117.roomconfig1;

import java.util.HashMap;
import java.util.Map;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;
import com.tvd12.ezyfox.core.annotation.RoomAgent;
import com.tvd12.ezyfox.core.entities.ApiRoom;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 *
 */
@Getter @Setter
@RoomAgent
@ResponseParams
public class V117Room1 extends ApiRoom {

    @ResponseParam("2")
    private Class<?> class2;
    
    @ResponseParam("1")
    public Class<?> getClass1() {
        return null;
    }
    
    @ResponseParam("3")
    public Map<String, String> prop() {
        return new HashMap<>();
    }
    
}
