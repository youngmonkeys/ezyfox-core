/**
 * 
 */
package com.tvd12.ezyfox.core.command;

/**
 * Execute this command to use system thread pool to execute a runnable task
 * 
 * @author tavandung12
 *
 */
public interface ExecuteTask {

    /**
     * Execute a task
     * 
     * @param task the task
     */
    void execute(Runnable task);
    
}
