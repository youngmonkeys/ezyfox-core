package com.tvd12.ezyfox.core.constants;

/**
 * Hold all server event
 * 
 * @author tavandung12
 *
 */
public interface ServerEvent {

	static final String SERVER_INITIALIZING = "SERVER_INIT";
	static final String BUDDY_ADD = "BUDDY_ADD";
	static final String BUDDY_BLOCK = "BUDDY_BLOCK";
	static final String BUDDY_LIST_INIT = "BUDDY_LIST_INIT";
	static final String BUDDY_MESSAGE = "BUDDY_MESSAGE";
	static final String BUDDY_ONLINE_STATE_UPDATE = "BUDDY_ONLINE_STATE_UPDATE";
	static final String BUDDY_REMOVE = "BUDDY_REMOVE";
	static final String BUDDY_VARIABLES_UPDATE = "BUDDY_VARIABLES_UPDATE";
	static final String GAME_INVITATION_FAILURE = "GAME_INVITATION_FAILURE";
	static final String GAME_INVITATION_SUCCESS = "GAME_INVITATION_SUCCESS";
	static final String PLAYER_TO_SPECTATOR = "PLAYER_TO_SPECTATOR";
	static final String PRIVATE_MESSAGE = "PRIVATE_MESSAGE";
	static final String PUBLIC_MESSAGE = "PUBLIC_MESSAGE";
	static final String ROOM_ADDED = "ROOM_ADDED";
	static final String ROOM_REMOVED = "ROOM_REMOVED";
	static final String ROOM_VARIABLES_UPDATE = "ROOM_VARIABLES_UPDATE";
	static final String SERVER_READY = "SERVER_READY";
	static final String SPECTATOR_TO_PLAYER = "SPECTATOR_TO_PLAYER";
	static final String USER_DISCONNECT = "USER_DISCONNECT";
	static final String USER_JOIN_ROOM = "USER_JOIN_ROOM";
	static final String USER_JOIN_ZONE = "USER_JOIN_ZONE";
	static final String USER_LEAVE_ROOM = "USER_LEAVE_ROOM";
	static final String USER_LOGIN = "USER_LOGIN";
	static final String USER_LOGOUT = "USER_LOGOUT";
	static final String USER_RECONNECTION_SUCCESS = "USER_RECONNECTION_SUCCESS";
	static final String USER_RECONNECTION_TRY = "USER_RECONNECTION_TRY";
	static final String USER_VARIABLES_UPDATE = "USER_VARIABLES_UPDATE";
}