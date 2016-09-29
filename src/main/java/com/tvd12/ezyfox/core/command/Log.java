package com.tvd12.ezyfox.core.command;

/**
 * Run this command to log information
 * 
 * @author tavandung12
 *
 */

public interface Log {

    /**
     * Set class to log
     * 
     * @param obj object to log
     * @return this pointer
     */
    Log from(Object obj);
	
	/**
	 * Log with info level
	 * 
	 * @param msg message
	 */
	void info(String msg);
	
	/**
	 * Log with debug level
	 * 
	 * @param msg message
	 */
	void debug(String msg);
	
	/**
	 * Log with warn level
	 * 
	 * @param msg message
	 */
	void warn(String msg);
	
	/**
	 * Log with error level
	 * 
	 * @param msg message
	 */
	void error(String msg);
	
	/**
	 * Log with info level
	 * 
	 * @param msg message
	 * @param e exception
	 */
	void info(String msg, Throwable e);
	
	/**
	 * Log with debug level
	 * 
	 * @param msg message
	 * @param e exception
	 */
	void debug(String msg, Throwable e);
	
	/**
	 * Log with warn level
	 * 
	 * @param msg message 
	 * @param e exception
	 */
	void warn(String msg, Throwable e);
	
	/**
	 * Log with error level
	 * 
	 * @param msg message
	 * @param e exception
	 */
	void error(String msg, Throwable e);
	
	/**
     * Log with info level
     * 
     * @param msg message
     * @param args arguments
     */
    void info(String msg, Object...args);
    
    /**
     * Log with debug level
     * 
     * @param msg message
     * @param args arguments
     */
    void debug(String msg, Object...args);
    
    /**
     * Log with warn level
     * 
     * @param msg message
     * @param args arguments
     */
    void warn(String msg, Object...args);
    
    /**
     * Log with error level
     * 
     * @param msg message
     * @param args arguments
     */
    void error(String msg, Object...args);
	
}
