package com.tvd12.ezyfox.core.command;

import com.tvd12.ezyfox.core.entities.ApiBaseUser;

/**
 * Run this command to ping client
 * 
 * @author tavandung12
 *
 */

public interface PingClient {
    
    /**
     * set delay time
     * 
     * @param time delay time
     * @return this pointer
     */
    PingClient delay(long time);

    /**
     * set schedule on time or not
     * 
     * @param value true or false
     * @return this pointer
     */
    PingClient oneTime(boolean value);
    
    /**
     * set period 
     * 
     * @param value period
     * @return this pointer
     */
    PingClient period(long value);
    
    /**
     * set task
     * 
     * @param value a task
     * @return this pointer
     */
    PingClient callback(Runnable value);
    
    /**
     * User (client) to ping
     * 
     * @param user the user
     * @return this pointer
     */
    PingClient user(ApiBaseUser user);
    
    /**
     * start schedule
     */
    void ping();
    
    /**
     * stop schedule after done
     */
    void stop();
    
    /**
     * stop schedule without waiting for task's done
     */
    void stopNow();
    
    /**
     * Indicate that the schedule is stopped or not
     * 
     * @return true or false
     */
    boolean stopped();
    
}
