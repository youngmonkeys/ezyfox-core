package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Run this command to ping client
 * 
 * @author tavandung12
 *
 */

public interface SchedulePing {
    
    /**
     * set delay time
     * 
     * @param time delay time
     * @return this pointer
     */
    SchedulePing delay(long time);

    /**
     * set period 
     * 
     * @param value period
     * @return this pointer
     */
    SchedulePing period(long value);
    
    /**
     * User (client) to ping
     * 
     * @param user the user
     * @return this pointer
     */
    SchedulePing user(ApiBaseUser user);
    
    /**
     * start schedule
     */
    void ping();
    
    /**
     * stop ping
     */
    void stop();
    
    /**
     * Indicate that the schedule is stopped or not
     * 
     * @return true or false
     */
    boolean stopped();
    
}
