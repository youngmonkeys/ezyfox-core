package com.tvd12.ezyfox.core.command;

/**
 * Run this command to schedule a task
 * 
 * @author tavandung12
 *
 */

public interface Schedule {
    
    /**
     * set delay time
     * 
     * @param time delay time
     * @return this pointer
     */
    Schedule delay(long time);

    /**
     * set schedule on time or not
     * 
     * @param value true or false
     * @return this pointer
     */
    Schedule oneTime(boolean value);
    
    /**
     * set period 
     * 
     * @param value period
     * @return this pointer
     */
    Schedule period(long value);
    
    /**
     * set task
     * 
     * @param value a task
     * @return this pointer
     */
    Schedule task(Runnable value);
    
    /**
     * start schedule
     */
    public void schedule();
    
    /**
     * stop schedule without waiting for task's done
     */
    public void stop();
    
    /**
     * Indicate that the schedule is stopped or not
     * 
     * @return true or false
     */
    public boolean stopped();
    
    /**
     * Attempts to cancel execution of this task but give a change to be done
     * 
     * @see java.util.concurrent.Future#cancel(boolean)
     * 
     * @return true or false
     */
    public boolean cancel();
    
    /**
     * Attempts to cancel execution of this task
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
