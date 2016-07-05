/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Execute this command to update buddy properties of an user
 * 
 * @author tavandung12
 *
 */
public interface UpdateBuddyProperties extends BaseCommand {

    /**
     * the owner User
     * 
     * @param user owner user 
     * @return this pointer
     */
    UpdateBuddyProperties owner(ApiBaseUser user);
    
    /**
     *  if true, send a client update
     * 
     * @param value true or false
     * @return this pointer
     */
    UpdateBuddyProperties fireClientEvent(boolean value);
    
    /**
     * if true, fire a server event (BUDDY_VARIABLES_UPDATE)
     * 
     * @param value true or false
     * @return this pointer
     */
    UpdateBuddyProperties fireServerEvent(boolean value);
    
    /**
     * exclude array of variables
     * 
     * @param varnames variable name array
     * @return this pointer
     */
    UpdateBuddyProperties exclude(String... varnames);
    
    /**
     * include array of variables, if have no included variable, all variables be accepted
     * 
     * @param varnames variable name array
     * @return this pointer
     */
    UpdateBuddyProperties include(String... varnames);
    
}
