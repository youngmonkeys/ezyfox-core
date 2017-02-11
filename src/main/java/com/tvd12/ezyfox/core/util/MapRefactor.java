/**
 * 
 */
package com.tvd12.ezyfox.core.util;

public interface MapRefactor<K, V, O> {
    O refact(K key, V value);
}