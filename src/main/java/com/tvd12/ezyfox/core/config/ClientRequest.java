/**
 * 
 */
package com.tvd12.ezyfox.core.config;

/**
 * @author tavandung12
 *
 */
public interface ClientRequest {
    static final String Handshake               = "Handshake";  
    static final String Login                   = "Login";  
    static final String Logout                  = "Logout";  
    static final String GetRoomList             = "GetRoomList";  
    static final String JoinRoom                = "JoinRoom";  
    static final String AutoJoin                = "AutoJoin";  
    static final String CreateRoom              = "CreateRoom";  
    static final String GenericMessage          = "GenericMessage";  
    static final String ChangeRoomName          = "ChangeRoomName";  
    static final String ChangeRoomPassword      = "ChangeRoomPassword";  
    static final String ObjectMessage           = "ObjectMessage";  
    static final String SetRoomVariables        = "SetRoomVariables";  
    static final String SetUserVariables        = "SetUserVariables";  
    static final String CallExtension           = "CallExtension";  
    static final String LeaveRoom               = "LeaveRoom";  
    static final String SubscribeRoomGroup      = "SubscribeRoomGroup";  
    static final String UnsubscribeRoomGroup    = "UnsubscribeRoomGroup";  
    static final String SpectatorToPlayer       = "SpectatorToPlayer";  
    static final String PlayerToSpectator       = "PlayerToSpectator";  
    static final String ChangeRoomCapacity      = "ChangeRoomCapacity";  
    static final String PublicMessage           = "PublicMessage";  
    static final String PrivateMessage          = "PrivateMessage";  
    static final String ModeratorMessage        = "ModeratorMessage";  
    static final String AdminMessage            = "AdminMessage";  
    static final String KickUser                = "KickUser";  
    static final String BanUser                 = "BanUser";  
    static final String ManualDisconnection     = "ManualDisconnection";  
    static final String FindRooms               = "FindRooms";  
    static final String FindUsers               = "FindUsers";  
    static final String PingPong                = "PingPong";  
    static final String SetUserPosition         = "SetUserPosition";  
    static final String InitBuddyList           = "InitBuddyList";  
    static final String AddBuddy                = "AddBuddy";  
    static final String BlockBuddy              = "BlockBuddy";  
    static final String RemoveBuddy             = "RemoveBuddy";  
    static final String SetBuddyVariables       = "SetBuddyVariables";  
    static final String GoOnline                = "GoOnline";  
    static final String BuddyMessage            = "BuddyMessage";  
    static final String InviteUser              = "InviteUser";  
    static final String InvitationReply         = "InvitationReply";  
    static final String CreateSFSGame           = "CreateSFSGame";  
    static final String QuickJoinGame           = "QuickJoinGame";  
    static final String GetLobbyNode            = "GetLobbyNode";  
    static final String KeepAlive               = "KeepAlive";  
    static final String OnEnterRoom             = "OnEnterRoom";  
    static final String OnRoomCountChange       = "OnRoomCountChange";  
    static final String OnUserLost              = "OnUserLost";  
    static final String OnRoomLost              = "OnRoomLost";  
    static final String OnUserExitRoom          = "OnUserExitRoom";  
    static final String OnClientDisconnection   = "OnClientDisconnection";  
    static final String OnReconnectionFailure   = "OnReconnectionFailure";  
    static final String OnMMOItemVariablesUpdate    = "OnMMOItemVariablesUpdate";
    
}
