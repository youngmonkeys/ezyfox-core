/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiRoom;

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
    public <T extends SpectatorToPlayer> T user(ApiBaseUser user);
    
    /**
     * the Room in which the spectator will be turned into a player
     * 
     * @param room the Room in which the player will be turned into a spectator
     * @return this pointer
     */
    public <T extends SpectatorToPlayer> T room(ApiRoom room);
    
    /**
     * if true send an update to the client (recommended)
     * 
     * @param value fire client event or not
     * @return this pointer
     */
    public <T extends SpectatorToPlayer> T fireClientEvent(boolean value);
    
    /**
     * if true fire a server side event
     * 
     * @param value fire a server side event or not
     * @return this pointer
     */
    public <T extends SpectatorToPlayer> T fireServerEvent(boolean value);
    
}
