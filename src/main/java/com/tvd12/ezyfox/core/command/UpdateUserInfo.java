/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiRoom;

/**
 * Execute this command to update information of an user
 * 
 * @author tavandung12
 *
 */
public interface UpdateUserInfo {

    /**
     * Add created room in the list
     * 
     * @param room room to add
     */
    void addCreatedRoom(ApiRoom room);
    
    /**
     * Add joined room in the list
     * 
     * @param room joined room
     */
    void addJoinedRoom(ApiRoom room);
    
    /**
     * Remove created room from list
     * 
     * @param room created room
     */
    void removeCreatedRoom(ApiRoom room);
    
    /**
     * Remove joined room from list
     * 
     * @param room joined room
     */
    void removeJoinedRoom(ApiRoom room);
    
    /**
     * Set number of bad word warnings
     * 
     * @param count count
     */
    void setBadWordsWarnings(int count);
    
    /**
     * Set being kicked
     * 
     * @param flag kicked or not
     */
    void setBeingKicked(boolean flag);
    
    /**
     * Set connected state
     * 
     * @param flag connected or not
     */
    void setConnected(boolean flag);
    
    /**
     * Set number of flood warnings
     * 
     * @param count count
     */
    void setFloodWarnings(int count);
    
    /**
     * Set joining a room state
     * 
     * @param flag joining or not
     */
    void setJoining(boolean flag);
    
    /**
     * Set last login time
     * 
     * @param lastLoginTime last login time
     */
    void setLastLoginTime(long lastLoginTime);
    
    /**
     * Set last request time
     * 
     * @param millis last request time in mini
     */
    void setLastRequestTime(long millis);
    
    /**
     * Set max allowed variables
     * 
     * @param max max allowed variables
     */
    void setMaxAllowedVariables(int max);
    
    /**
     * Set player id for an user at the room
     * 
     * @param id player id
     * @param room the room
     */
    void setPlayerId(int id, ApiRoom room);
    
    /**
     * Set player id for an user at current room
     * 
     * @param id player id
     */
    void setPlayerId(int id);
    
    /**
     * Set privilegeId for user
     * 
     * @param id privilegeId
     */
    void setPrivilegeId(short id);
    
    /**
     * Set reconnection seconds for user
     * 
     * @param seconds time in seconds
     */
    void setReconnectionSeconds(int seconds);
    
    /**
     * Subscribe a group
     * 
     * @param groupId group id
     */
    void subscribeGroup(String groupId);
    
    /**
     * Unsubscribe a group
     * 
     * @param groupId group id
     */
    void unsubscribeGroup(java.lang.String groupId);
    
    /**
     * Update last request time for user
     */
    void updateLastRequestTime();
    
    /**
     * Remove all user variables
     */
    void removeAllVariables();
}
