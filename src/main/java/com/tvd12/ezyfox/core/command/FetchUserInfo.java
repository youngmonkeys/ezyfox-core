/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import java.util.List;
import java.util.Map;

import com.tvd12.ezyfox.core.model.ApiRoom;
import com.tvd12.ezyfox.core.model.ApiZone;

/**
 * Execute this command to get information of an user
 * 
 * @author tavandung12
 *
 */
public interface FetchUserInfo {

    /**
     * Check if the User is kicked
     * 
     * @return true or false
     */
    boolean isBeingKicked();
    
    /**
     * Check if the User is connected
     * 
     * @return
     */
    boolean isConnected();
    
    /**
     * Check if a User is joined in a Room 
     * 
     * @param room room to check
     * @return true or false
     */
    boolean isJoinedInRoom(ApiRoom room);
    
    /**
     * Check if a User is joining in a Room
     * 
     * @return true or false
     */
    boolean isJoining();
    
    /**
     * Returns true if the User is connected to the local cluster node
     * 
     * @return true or false
     */
    boolean isLocal();
    
    /**
     * Returns the NPC flag
     * 
     * @return true or false
     */
    boolean isNPC();
    
    /**
     * Check if the User is a Player in the current Room (only for Game Rooms)
     * 
     * @return true or false
     */
    boolean isPlayer();
    
    /**
     * Check if the User is Player in a specific Room
     * 
     * @param room room to check
     * @return true or false
     */
    boolean isPlayer(ApiRoom room);
    
    /**
     * Check if the User is a Spectator in the current Room (only for Game Rooms)
     * 
     * @return true or false
     */
    boolean isSpectator();
    
    /**
     * Check if the User is Spectator in a specific Room
     * 
     * @param room room to check
     * @return true or false
     */
    boolean isSpectator(ApiRoom room);
    
    /**
     * Check if the User is subscribed to a certain Room Group
     * 
     * @param groupId room group's id
     * @return true or false
     */
    boolean isSubscribedToGroup(String groupId);
    
    /**
     * Check if the User has SuperUser capabilities (kicking/banning etc...)
     * 
     * @return true or false
     */
    boolean isSuperUser();
    
    /**
     * Return the Privilege ID of the User.
     * 
     * @return the Privilege ID of the User.
     */
    short getPrivilegeId();
    
    /**
     * Get number of warning from system when use bad word 
     * 
     * @return number of warning from system when use bad word
     */
    int getBadWordsWarnings();
    
    /**
     * Get a list of Rooms created by this User
     * 
     * @return a list of rooms
     */
    List<ApiRoom> getCreatedRooms();
    
    /**
     * Get number of warning from system when flood
     * 
     * @return number of warning
     */
    int getFloodWarnings();
    
    /**
     * A list of Rooms currently joined by the User
     * 
     * @return a list of rooms
     */
    List<ApiRoom> getJoinedRooms();
    
    /**
     * A reference to the last Room that was joined by this User
     * 
     * @return a reference or room
     */
    <T extends ApiRoom> T getLastJoinedRoom();
    
    /**
     * Get the user last request time (Unix timestamp)
     * 
     * @return the last request time
     */
    long getLastRequestTime();
    
    /**
     * Get the user login time (Unix timestamp)
     * 
     * @return the login time
     */
    long getLoginTime();
    
    /**
     * Get the maximum allowed User Variables for this User
     * 
     * @return the maximum allowed user variables
     */
    int getMaxAllowedVariables();
    
    /**
     * Get the number of Rooms created by the User
     * 
     * @return number of rooms
     */
    int getOwnedRoomsCount();
    
    /**
     * Get the playerId of the User (if applicable).
     * 
     * @return player id of user
     */
    int getPlayerId();
    
    /**
     * Get the playerId of the User in the room
     * 
     * @param room the room
     * @return player id
     */
    int getPlayerId(ApiRoom room);
    
    /**
     * Get a map of playerId(s) per Room 
     * This method can be used when a player is currently engaged in multiple games at the same time
     * 
     * @return a map of playerId(s) per room
     */
    Map<ApiRoom, Integer> getPlayerIds();
    
    /**
     * Get reconnection seconds of user
     * 
     * @return  reconnection seconds
     */
    int getReconnectionSeconds();
    
    /**
     * Get a list of Room Groups subscribed by the User
     * @return list of room groupIds
     */
    List<String> getSubscribedGroups();
    
    /**
     * Return the number of UserVariables for this User
     * 
     * @return
     */
    int getVariablesCount();
    
    /**
     * Get the Zone where the User is currently logged in
     * 
     * @return the zone reference
     */
    ApiZone getZone();
    
}
