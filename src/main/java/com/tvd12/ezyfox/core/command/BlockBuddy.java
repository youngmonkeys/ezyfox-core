/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Execute this command to Block/Unblock a Buddy in the owner's BuddyList Blocked buddies 
 * won't be able to see the owner online status and send him messages or updates
 * 
 * @author tavandung12
 *
 */
public interface BlockBuddy extends BaseCommand {

    /**
     * buddy's owner
     * 
     * @param owner owner
     * @return this pointer
     */
    BlockBuddy owner(ApiBaseUser owner);
    
    /**
     * name of buddy's owner
     * 
     * @param ownerName owner name
     * @return this pointer
     */
    BlockBuddy owner(String ownerName);
    
    /**
     * name of buddy to add
     * 
     * @param buddyName buddy name
     * @return this pointer
     */
    BlockBuddy buddy(String buddyName);
    
    /**
     * Set the 'blocked' flag
     * 
     * @param isBlock the 'blocked' flag
     * @return this pointer
     */
    BlockBuddy blocked(boolean isBlock);
    
    /**
     * if true, send a client update
     * 
     * @param fireClientEvent if true, send a client update
     * @return this pointer
     */
    BlockBuddy fireClientEvent(boolean fireClientEvent);
    
    /**
     * if true, fire a server event (BUDDY_REMOVE)
     * 
     * @param fireServerEvent if true, fire a server event (BUDDY_REMOVE)
     * @return this pointer
     */
    BlockBuddy fireServerEvent(boolean fireServerEvent);
}
