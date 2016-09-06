/**
 * 
 */
package com.tvd12.ezyfox.core.command;

/**
 * Execute this command to add a command class to the application context
 * 
 * @author tavandung12
 *
 */
public interface AddCommand {

    /**
     * Add the command class to the application context
     * 
     * @param baseClass the interface or abstract class
     * @param implementation the implementation class
     * @return the this pointer for chaining
     */
    AddCommand add(Class<?> baseClass, Class<?> implementation);
    
}
