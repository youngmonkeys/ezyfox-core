package com.tvd12.ezyfox.core.transport.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @see Parameters
 * 
 * @author tavandung12
 *
 */

public class ParameterWrapper implements Parameters, Serializable {
	private static final long serialVersionUID = 566241209233835941L;
	
	// key-value map
    protected Map<Object, Object> values
            = new HashMap<>();
    
    /**
     * @see Parameters#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /**
     * @see Parameters#contain(Object)
     */
    @Override
    public boolean contain(Object key) {
        return values.containsKey(key);
    }

    /**
     * @see Parameters#setAll(Map)
     */
    @Override
    public void setAll(Map<Object, Object> values) {
        this.values.putAll(values);
    }
    
    /**
     * @see Parameters#set(Object, Object)
     */
    @Override
    public Object set(Object key, Object value) {
        return values.put(key, value);
    }

    /**
     * @see Parameters#get(Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key) {
        return (T) values.get(key);
    }

    /**
     * @see Parameters#get(Object, Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return (T)get(key);
    }
    
    /**
     * @see Parameters#size()
     */
    @Override
    public int size() {
        return values.size();
    }
    
    /**
     * @see Parameters#keys()
     */
    @Override
    public Set<Object> keys() {
        return values.keySet();
    }
    
    /**
     * @see Parameters#values()
     */
    @Override
    public List<Object> values() {
        return new ArrayList<>(values.values());
    }
    
    /**
     * @see Parameters#clear()
     */
    @Override
    public void clear() {
        values.clear();
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.transport.Parameters#toMap()
     */
    @Override
    public Map<Object, Object> toMap() {
        return values;
    }
}
