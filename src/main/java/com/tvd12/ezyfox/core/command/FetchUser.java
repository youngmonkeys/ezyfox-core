/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command to fetch an user
 * 
 * @author tavandung12
 *
 */
public interface FetchUser extends BaseCommand {

    /**
     * user id
     * 
     * @param userId user id
     * @return this pointer
     */
    <T extends FetchUser> T userId(int userId);
    
    /**
     * user name 
     * 
     * @param name user name
     * @return this pointer
     */
    <T extends FetchUser> T username(String name);
    
    /**
     * the room
     * 
     * @param room the room
     * @return this pointer
     */
    <T extends FetchUser> T room(ApiRoom room);
    
}
