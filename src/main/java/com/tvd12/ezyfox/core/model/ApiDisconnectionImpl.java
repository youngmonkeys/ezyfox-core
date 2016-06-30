/**
 * 
 */
package com.tvd12.ezyfox.core.model;

import java.util.List;
import java.util.Map;

import lombok.Setter;

/**
 * @author tavandung12
 *
 */
@Setter
public class ApiDisconnectionImpl implements ApiDisconnection {

    private ApiBaseUser user;
    private ApiZone zone;
    @SuppressWarnings("rawtypes")
    private List joinedRooms;
    @SuppressWarnings("rawtypes")
    private Map playerIdsByRoom;
    private String reason;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiDisconnection#user()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T user() {
        return (T)this.user;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiDisconnection#zone()
     */
    @Override
    public ApiZone zone() {
        return this.zone;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiDisconnection#joinedRooms()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiRoom> List<T> joinedRooms() {
        return this.joinedRooms;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiDisconnection#playerIdsByRoom()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiRoom> Map<T, Integer> playerIdsByRoom() {
        return this.playerIdsByRoom;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.model.ApiDisconnection#reason()
     */
    @Override
    public String reason() {
        return this.reason;
    }

    public ApiDisconnectionImpl clone() {
        ApiDisconnectionImpl clone = new ApiDisconnectionImpl();
        clone.setJoinedRooms(joinedRooms);
        clone.setPlayerIdsByRoom(playerIdsByRoom);
        clone.setReason(reason);
        clone.setUser(user);
        clone.setZone(zone);
        return clone;
    }
}
