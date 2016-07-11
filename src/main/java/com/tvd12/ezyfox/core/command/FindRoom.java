package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Run this command to get a room by name or by user
 * 
 * @author tavandung12
 *
 */
public interface FindRoom {
    
    /**
     * get room by room's id
     * 
     * @param id room's id
     * @return this pointer
     */
    <T> T by(int id);

    /**
     * get room by room's name
     * 
     * @param name room's name
     * @return this pointer
     */
	<T> T by(String name);
	
	/**
	 * get room by user in room
	 * 
	 * @param user user in room
	 * @return this pointer
	 */
	<T> T by(ApiBaseUser user);
	
}
