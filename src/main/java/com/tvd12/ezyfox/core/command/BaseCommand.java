package com.tvd12.ezyfox.core.command;

/**
 * Basic command
 * 
 * @author tavandung12
 *
 */

public interface BaseCommand {

    /**
     * executive method, call this method to run command
     * 
     * @return result of execution
     */
	<T> T execute();
	
}
