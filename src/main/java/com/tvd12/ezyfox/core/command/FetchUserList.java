/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;

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
     * @param room the room
     * @return list of users
     */
    List<ApiUser> in(ApiRoom room);
    
}
