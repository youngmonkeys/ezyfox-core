/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Subscribe User to a Room Group. This will enable the User to receive events for Rooms in that Group
 * 
 * @author tavandung12
 *
 */
public interface SubscribeRoomGroup extends BaseCommand {

    /**
     * The User will subscribe to a room group
     * 
     * @param user the user
     * @return this pointer
     */
    public <T extends SubscribeRoomGroup> T user(ApiBaseUser user);
    
    /**
     * the group name
     * 
     * @param groupId the group name
     * @return this pointer
     */
    public <T extends SubscribeRoomGroup> T groupId(String groupId);
    
}
