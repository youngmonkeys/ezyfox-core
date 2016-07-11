/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to turns a spectator in a Game Room to a player.
 * 
 * @author tavandung12
 *
 */
public interface SpectatorToPlayer extends BaseCommand {
    
    /**
     * spectator to turn to player
     * 
     * @param user spectator to turn to player
     * @return this pointer
     */
    SpectatorToPlayer user(ApiBaseUser user);
    
    /**
     * the Room in which the spectator will be turned into a player
     * 
     * @param room the Room in which the player will be turned into a spectator
     * @return this pointer
     */
    SpectatorToPlayer room(ApiRoom room);
    
    /**
     * if true send an update to the client (recommended)
     * 
     * @param value fire client event or not
     * @return this pointer
     */
    SpectatorToPlayer fireClientEvent(boolean value);
    
    /**
     * if true fire a server side event
     * 
     * @param value fire a server side event or not
     * @return this pointer
     */
    SpectatorToPlayer fireServerEvent(boolean value);
    
}
