/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

import java.util.List;

/**
 * @author tavandung12
 *
 */
public abstract class ApiGameRoom extends ApiRoom {

    /**
     * Get list of players in a game corresponding to game user class
     * 
     * @param <T> the game user type
     * @return list of player in a game
     */
    public <T extends ApiGameUser> List<T> getPlayers() {
        return command.getPlayersList(userClass());
    }
    
    /**
     * Get list of spectators in a game corresponding to game user class
     * 
     * @param <T> the game user type
     * @return list of player in a game
     */
    public <T extends ApiGameUser> List<T> getSpectators() {
        return command.getSpectatorsList(userClass());
    }
    
    /**
     * Get list of users in a game corresponding to game user class
     * 
     * @param <T> the game user type
     * @return list of player in a game
     */
    public <T extends ApiGameUser> List<T> getUsers() {
        return command.getUserList(userClass());
    }
    
    /**
     * switch user from player to spectator
     * 
     * @param user the user
     */
    public void switchPlayerToSpectator(ApiBaseUser user) {
        command.switchPlayerToSpectator(user);
    }
    
    /**
     * switch user from spectator to player
     * 
     * @param user the user
     */
    public void switchSpectatorToPlayer(ApiBaseUser user) {
        command.switchSpectatorToPlayer(user);
    }
    
    /**
     * Class of game user
     * 
     * @return class of game user
     */
    protected abstract Class<?> userClass();
    
}
