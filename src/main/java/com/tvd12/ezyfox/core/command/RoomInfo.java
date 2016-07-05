/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiRoom;

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
    RoomInfo room(ApiRoom room);
    
    /**
     * Set the room id
     * 
     * @param id the room id
     * @return this pointer
     */
    RoomInfo room(int id);
    
    /**
     * Set the room name
     * 
     * @param name the room name
     * @return this pointer
     */
    RoomInfo room(String name);
    
}
