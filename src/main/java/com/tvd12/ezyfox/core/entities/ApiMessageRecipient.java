/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

/**
 * @author tavandung12
 *
 */
public interface ApiMessageRecipient {

    /**
     * message's recipient (only for private message)
     * 
     * @param <T> the user type
     * @return message's recipient
     */
    public <T extends ApiBaseUser> T recipient();
    
}
