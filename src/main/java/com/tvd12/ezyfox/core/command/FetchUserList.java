/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

import com.tvd12.ezyfox.core.entities.ApiGameUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;
import com.tvd12.ezyfox.core.entities.ApiUser;

/**
 * Execute this command to fetch user list
 * 
 * @author tavandung12
 *
 */
public interface FetchUserList {

    /**
     * Get all users in a room
     * 
     * @param <T> the user type
     * @param room the room
     * @return list of users
     */
    <T extends ApiUser> List<T> in(ApiRoom room);
    
    /**
     * Get all users in a room
     * 
     * @param <T> the game user type
     * @param clazz class of game user
     * @param room the room
     * @return list of users
     */
    <T extends ApiGameUser> List<T> in(ApiRoom room, Class<?> clazz);
}
