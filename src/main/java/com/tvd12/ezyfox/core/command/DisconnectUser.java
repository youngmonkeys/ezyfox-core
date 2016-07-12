/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

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
    DisconnectUser user(ApiBaseUser userToDisconnect);
    
    /**
     * User's name to disconnect
     * 
     * @param usernameToDisconnect name of user to disconnect
     * @return this pointer
     */
    DisconnectUser user(String usernameToDisconnect);
    
    /**
     * Disconnect reason id
     * IDLE(0),  KICK(1),  BAN(2),  UNKNOWN(3);
     * 
     * @param id id
     * @return this pointer
     */
    DisconnectUser reason(byte id);
    
}
