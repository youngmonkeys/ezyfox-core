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
     * stop ping
     */
    void stop();
    
    /**
     * Indicate that the schedule is stopped or not
     * 
     * @return true or false
     */
    boolean stopped();
    
    /**
     * Attempts to cancel execution of this ping but give a change to be done
     * 
     * @see java.util.concurrent.Future#cancel(boolean)
     * 
     * @return true or false
     */
    public boolean cancel();
    
    /**
     * Attempts to cancel execution of this ping
     * 
     * @see java.util.concurrent.Future#cancel(boolean)
     * 
     * @return true or false
     */
    public boolean cancelNow();
    
    /**
     * @see java.util.concurrent.Future#isCancelled()
     * 
     * @return true or false
     */
    public boolean cancelled();
    
    /**
     * @see java.util.concurrent.Future#isDone()
     * 
     * @return true or false
     */
    public boolean done();
    
}
