/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Unsubscribe User form a Room Group.
 * 
 * @author tavandung12
 *
 */
public interface UnsubscribeRoomGroup extends BaseCommand {

    /**
     * The User will unsubscribe from a room group
     * 
     * @param user the user
     * @return this pointer
     */
    UnsubscribeRoomGroup user(ApiBaseUser user);
    
    /**
     * the group name
     * 
     * @param groupId the group name
     * @return this pointer
     */
    UnsubscribeRoomGroup groupId(String groupId);
    
}
