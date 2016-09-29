package com.tvd12.ezyfox.core.transport.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * Support to transport data between objects int concurrent enviroment
 * 
 * @author tavandung12
 *
 */

public class ConcurrentParameters implements Parameters {

    // concurrent has map
    private ConcurrentHashMap<Object, Object> values 
            = new ConcurrentHashMap<>();
    
    /**
     * @see Parameters#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }
    
    /**
     * @see Parameters#size()
     */
    @Override
    public int size() {
        return values.size();
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
    @Override
    public Object get(Object key) {
        return values.get(key);
    }

    /**
     * @see Parameters#get(Object, Class)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> clazz) {
        return (T)values.get(key);
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
     * @see ConcurrentHashMap#putIfAbsent(Object, Object) 
     * 
     * @param key the key
     * @param value the value
     * @return the replaced value
     */
    public Object setIfAbsent(Object key, Object value) {
        return values.putIfAbsent(key, value);
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
