/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to disconnect User abruptly
 * 
 * @author tavandung12
 *
 */
public interface DisconnectUser extends BaseCommand {

    /**
     * User to disconnect
     * 
     * @param userToDisconnect user to disconnect
     * @return this pointer
     */
    public <T extends DisconnectUser> T user(ApiBaseUser userToDisconnect);
    
    /**
     * User's name to disconnect
     * 
     * @param usernameToDisconnect name of user to disconnect
     * @return this pointer
     */
    public <T extends DisconnectUser> T user(String usernameToDisconnect);
    
    /**
     * Disconnect reason id
     * 
     * @param id id
     * @return this pointer
     */
    public <T extends DisconnectUser> T reasonId(byte id);
    
}
