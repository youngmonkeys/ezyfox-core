/**
 * 
 */
package com.tvd12.ezyfox.core.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tavandung12
 *
 */
public class ApiBuddyProperties extends ApiModel {

    @Setter @Getter
    private String nickName;
    
    @Setter @Getter
    private String state;
    
    @Setter @Getter
    private boolean inited;
    
    @Setter @Getter
    private boolean online;
    
}
