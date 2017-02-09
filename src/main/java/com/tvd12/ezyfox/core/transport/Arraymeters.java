package com.tvd12.ezyfox.core.transport;

import java.util.Collection;

public interface Arraymeters extends RoArraymeters {
	
	/**
	 * Add values to array
	 * 
	 * @param values the values
	 */
	void add(Object... values);
	
	/**
	 * Add values to array
	 * 
	 * @param values the values
	 */
	void add(Collection<Object> values);
	
	/**
	 * Set value at the index
	 * 
	 * @param index the index
	 * @param value the value
	 */
	void set(int index, Object value);
	
	/**
	 * Remove value at the index
	 * 
	 * @param index the index
	 */
	void remove(int index);
	
	
}
