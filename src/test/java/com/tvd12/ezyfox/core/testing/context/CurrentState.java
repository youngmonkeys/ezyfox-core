/**
 * 
 */
package com.tvd12.ezyfox.core.testing.context;

import com.tvd12.ezyfox.core.annotation.ResponseParam;
import com.tvd12.ezyfox.core.annotation.ResponseParams;

import lombok.AllArgsConstructor;

/**
 * @author tavandung12
 *
 */
@ResponseParams
@AllArgsConstructor
public class CurrentState {

    protected VideoPokerRoom room;
    
    @ResponseParam("u")
    public String user() {
        return "dung";
    }
    
    @ResponseParam("2")
    public int currentTime() {
        return 0;
    }
    
}
