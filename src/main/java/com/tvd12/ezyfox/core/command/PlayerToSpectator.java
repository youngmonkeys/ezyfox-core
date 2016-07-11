/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to turns a player in a Game Room to a spectator.
 * 
 * @author tavandung12
 *
 */
public interface PlayerToSpectator extends BaseCommand {
    
    /**
     * user to turn to spectator
     * 
     * @param user user to turn to spectator
     * @return this pointer
     */
    PlayerToSpectator user(ApiBaseUser user);
    
    /**
     * the Room in which the player will be turned into a spectator
     * 
     * @param room the Room in which the player will be turned into a spectator
     * @return this pointer
     */
    PlayerToSpectator room(ApiRoom room);
    
    /**
     * if true send an update to the client (recommended)
     * 
     * @param value fire client event or not
     * @return this pointer
     */
    PlayerToSpectator fireClientEvent(boolean value);
    
    /**
     * if true fire a server side event
     * 
     * @param value fire a server side event or not
     * @return this pointer
     */
    PlayerToSpectator fireServerEvent(boolean value);
    
}
