package com.tvd12.ezyfox.core.entities;

import java.util.List;

public interface ApiZone extends ApiProperties {

	/**
	 * 
	 * @return a numeric ID used by the AdminTool
	 */
	int getId();
	
	/**
	 * 
	 * @return the maximum number of Rooms that can be created in the Room.
	 */
	int getMaxAllowedRooms();
	
	/**
	 * 
	 * @return  the maximum number of users allowed to join the Zone.
	 */
	int getMaxAllowedUsers(); 
	
	/**
	 * 
	 * @return the maximum number of characters allowed for a Room name
	 */
	int getMaxRoomNameChars();
	
	/**
	 * 
	 * @return the maximum number of Rooms that a User can create at once
	 */
	int getMaxRoomsCreatedPerUserLimit();
	
	/**
	 * 
	 * @return The maximum number of Room Variables allowed for each Room
	 */
	int getMaxRoomVariablesAllowed();
	
	/**
	 * 
	 * @return the max allowed idle time for a User
	 */
	int getMaxUserIdleTime();
	
	/**
	 * 
	 * @return the maximum number of User Variables allowed for each User
	 */
	int getMaxUserVariablesAllowed();
	
	/**
	 * 
	 * @return the minimum number of characters allowed for Room name
	 */
	int getMinRoomNameChars();
	
	/**
	 * 
	 * @return total of rooms in zone
	 */
	int getTotalRoomCount(); 
	
	/**
	 * 
	 * @return the current amount of Users connected to the Zone
	 */
	int getUserCount();
	
	/**
	 * 
	 * @return  the amount of seconds available for 
	 * a User to reconnect to the system in case their socket connection goes down.
	 */
	int getUserReconnectionSeconds();
	
	/**
	 * Checks if the Zone is currently enabled in the system or not.
	 * 
	 * @return true or false
	 */
	boolean isActive();
	
	/**
	 * 
	 * @return name of zone
	 */
	String getName();
	
	/**
	 * Get a User from its unique ID
	 * 
	 * @param <T> the user type
	 * @param id unique ID
	 * @return a reference
	 */
	<T extends ApiUser> T getUserById(int id);
	
	/**
	 * Get a User from its name
	 * 
	 * @param <T> the user type
	 * @param username user name
	 * @return a reference
	 */
	<T extends ApiUser> T getUserByName(String username);
	
	/**
	 * Get a list of Users from all Rooms in the provided Room Group
	 * 
	 * @param <T> the user type
	 * @param groupId the group id
	 * @return a reference
	 */
	<T extends ApiUser> List<T> getUsersInGroup(String groupId);
	
	/**
	 * Get all users in the zone
	 * 
	 * @param <T> the user type
	 * @return list of user
	 */
	<T extends ApiUser> List<T> getUserList();
	
	/**
	 * 
	 * Check whether user is a NPC
	 * 
	 * @param username user name
	 * @return is a NPC or not
	 */
	boolean isNPC(String username);

	/**
	 * Get room by id
	 * 
	 * @param <T> the room type
	 * @param id room id
	 * @return a room
	 */
	<T extends ApiRoom> T getRoomById(int id);
	
	/**
	 * Get room by name
	 * 
	 * @param <T> the room type
	 * @param name room name
	 * @return a room
	 */
	<T extends ApiRoom> T getRoomByName(String name);
	
	/**
	 * Get all rooms in a group
	 * 
	 * @param <T> the room type
	 * @param groupId group id
	 * @return list of rooms
	 */
	<T extends ApiRoom> List<T> getRoomsInGroup(String groupId);
	
	/**
	 * Get all rooms in the zone
	 * 
	 * @param <T> the room type
	 * @return list of rooms
	 */
	<T extends ApiRoom> List<T> getRoomList();
	
}
