package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;
import com.tvd12.ezyfox.core.entities.ApiRoom;

/**
 * Execute this command to update room variables
 * 
 * @author tavandung12
 *
 */

public interface UpdateRoom extends BaseCommand {
    
    /**
     * Send update to client? 
     * 
     * @param value true or false
     * @return this pointer
     */
	UpdateRoom toClient(boolean value);
	
	/**
	 * Set room agent
	 * 
	 * @param room room agent
	 * @return this pointer
	 */
	UpdateRoom room(ApiRoom room);
	
	/**
	 * Set user agent (who active update)
	 * 
	 * @param user user agent
	 * @return this pointer
	 */
	UpdateRoom user(ApiBaseUser user);
	
	/**
     * exclude array of variables
     * 
     * @param varnames variable name array
     * @return this pointer
     */
    UpdateRoom exclude(String... varnames);
    
    /**
     * include array of variables, if have no included variable, all variables be accepted
     * 
     * @param varnames variable name array
     * @return this pointer
     */
    UpdateRoom include(String... varnames);
}
