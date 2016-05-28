/**
 * 
 */
package com.tvd12.ezyfox.core.model;

/**
 * Support to wrap message content
 * 
 * @author tavandung12
 *
 */
public interface ApiBaseMessage {

    /**
     * message content's
     * 
     * @return message content's
     */
    public String content();
    
    /**
     * The zone
     * 
     * @return the zone
     */
    public ApiZone zone();
    
    /**
     * message's sender
     * 
     * @return message's sender
     */
    public <T extends ApiBaseUser> T sender();
    
}
