package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to update user variables
 * 
 * @author tavandung12
 *
 */
public interface UpdateUser extends BaseCommand {

    /**
     * Send update to client?
     * 
     * @param value true or false
     * @return this pointer
     */
	<T extends UpdateUser> T toClient(boolean value);
	
	/**
	 * set user agent
	 * 
	 * @param user user agent
	 * @return this pointer
	 */
	<T extends UpdateUser> T user(ApiBaseUser user);
	
	/**
	 * exclude array of variables
	 * 
	 * @param varnames variable name array
	 * @return this pointer
	 */
	<T extends UpdateUser> T exclude(String... varnames);
	
	/**
	 * include array of variables, if have no included variable, all variables be accepted
	 * 
	 * @param varnames variable name array
	 * @return this pointer
	 */
	<T extends UpdateUser> T include(String... varnames);
	
}
