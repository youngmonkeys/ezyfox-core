/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;
import com.tvd12.ezyfox.core.model.ApiZone;

/**
 * Execute this command to removes a new buddy from the BuddyList of the specified User.
 * 
 * @author tavandung12
 *
 */
public interface RemoveBuddy extends BaseCommand {

    /**
     * buddy's owner
     * 
     * @param owner owner
     * @return this pointer
     */
    public <T extends RemoveBuddy> T owner(ApiBaseUser owner);
    
    /**
     * name of buddy's owner
     * 
     * @param ownerName owner name
     * @return this pointer
     */
    public <T extends RemoveBuddy> T owner(String ownerName);
    
    /**
     * If you set zone this command will remove a buddy from the User's buddy list 
     * even if the User is not online at the moment This feature is not implemented yet.
     * 
     * @param zone which zone user's now in
     * @return this pointer
     */
    public <T extends RemoveBuddy> T zone(ApiZone zone);
    
    /**
     * name of buddy to add
     * 
     * @param buddyName buddy name
     * @return this pointer
     */
    public <T extends RemoveBuddy> T buddy(String buddyName);
    
    /**
     * if true, send a client update
     * 
     * @param fireClientEvent if true, send a client update
     * @return this pointer
     */
    public <T extends RemoveBuddy> T fireClientEvent(boolean fireClientEvent);
    
    /**
     * if true, fire a server event (BUDDY_REMOVE)
     * 
     * @param fireServerEvent if true, fire a server event (BUDDY_REMOVE)
     * @return this pointer
     */
    public <T extends RemoveBuddy> T fireServerEvent(boolean fireServerEvent);
    
}
