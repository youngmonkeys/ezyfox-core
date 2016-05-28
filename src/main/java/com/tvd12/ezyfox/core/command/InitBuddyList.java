/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to initialize the buddy list for the requested User. 
 * This involves loading previous data from the BuddyList storage.
 * 
 * @author tavandung12
 *
 */
public interface InitBuddyList extends BaseCommand {

    /**
     * the User
     * 
     * @param user the User
     * @return this pointer
     */
    public <T extends InitBuddyList> T user(ApiBaseUser user);
    
    /**
     * User's name
     * 
     * @param username user name
     * @return this pointer
     */
    public <T extends InitBuddyList> T user(String username);
    
    /**
     * if true fires a server side event (BUDDY_LIST_INIT)
     * 
     * @param value if true fires a server side event (BUDDY_LIST_INIT)
     * @return this pointer
     */
    public <T extends InitBuddyList> T fireServerEvent(boolean value);
    
}
