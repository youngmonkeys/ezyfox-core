/**
 * 
 */
package com.tvd12.ezyfox.core.entities;

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
     * @see com.tvd12.ezyfox.core.entities.ApiDisconnection#user()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiBaseUser> T user() {
        return (T)this.user;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiDisconnection#zone()
     */
    @Override
    public ApiZone zone() {
        return this.zone;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiDisconnection#joinedRooms()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiRoom> List<T> joinedRooms() {
        return this.joinedRooms;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiDisconnection#playerIdsByRoom()
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ApiRoom> Map<T, Integer> playerIdsByRoom() {
        return this.playerIdsByRoom;
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.entities.ApiDisconnection#reason()
     */
    @Override
    public String reason() {
        return this.reason;
    }
}
