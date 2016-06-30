/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command 
 * 
 * @author tavandung12
 *
 */
public interface RoomInfo extends FetchRoomInfo, UpdateRoomInfo {

    /**
     * Set the room
     * 
     * @param room the room
     * @return this pointer
     */
    <T extends RoomInfo> T room(ApiRoom room);
    
}
