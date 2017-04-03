package com.tvd12.ezyfox.core.content;

/**
 * 
 * Application context support for:
 * - connecting the system components
 * - hold system configuration
 * - communicate with game server environment via command mechanism
 * - set/get properties
 * 
 * @author tavandung12
 *
 */
public interface AppContext {
	 
	/**
	 * @param <T> the command type
	 * @param clazz command class or interface
	 * @return a command object
	 */
	<T> T command(Class<T> clazz);
	
	/**
	 * Support for holding data
	 * 
	 * @param key key
	 * @param value value
	 */
	void set(Object key, Object value);
	
	/**
     * Support to retrieve data that's held
     * 
     * @param <T> the returned type
     * @param key key
     * @return a value
     */
    <T> T get(Object key);
    
    /**
     * Support to retrieve instance of the class that's held
     * 
     * @param <T> the returned type
     * @param clazz the class mapped to the instance
     * @return the instance of the class
     */
    <T> T get(Class<T> clazz);
	
	/**
	 * Support to retrieve data that's held
	 * 
	 * @param <T> the returned type
	 * @param key key
	 * @param clazz type of value
	 * @return a value
	 */
	<T> T get(Object key, Class<T> clazz);
	
	/**
	 * Check contains key or not
	 * 
	 * @param key the ke
	 * @return true or false
	 */
	boolean contains(Object key);
	
}
