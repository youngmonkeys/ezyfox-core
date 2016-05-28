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
	 * 
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
	 * Support for retrieve data that's held
	 * 
	 * @param key key
	 * @param clazz type of value
	 * @return a value
	 */
	<T> T get(Object key, Class<T> clazz);
	
}
