/**
 * 
 */
package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.model.ApiBaseUser;

/**
 * Execute this command to update information of a room
 * 
 * @author tavandung12
 *
 */
public interface UpdateRoomInfo {

    void addUser(ApiBaseUser user);
    
    void addUser(ApiBaseUser user, boolean asSpectator);
    
    void destroy();
    
    void removeUser(ApiBaseUser user);
    
    void removeVariable(String varname);
    
    void removeAllVariables();
    
    void setActive(boolean flag);
    
    void setCapacity(int maxUser, int maxSpectators);
    
    void setDynamic(boolean dynamic);
    
    void setGame(boolean game);
    
    void setGroupId(String groupId); 
    
    void setHidden(boolean hidden);
    
    void setMaxRoomVariablesAllowed(int max);
    
    void setMaxSpectators(int maxSpectators);
    
    void setMaxUsers(int maxUsers);
    
    void setName(java.lang.String name);
    
    void setOwner(ApiBaseUser owner);
    
    void setPassword(String password);
    
    void setUseWordsFilter(boolean useWordsFilter);
    
    void switchPlayerToSpectator(ApiBaseUser user);
    
    void switchSpectatorToPlayer(ApiBaseUser user);
    
}
