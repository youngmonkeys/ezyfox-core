/**
 * 
 */
package com.tvd12.ezyfox.core.structure;

/**
 * @author tavandung12
 *
 */
public interface TypeFilter {

    /**
     * Filter type
     * 
     * @param type the type
     * @return ignored or not
     */
    boolean filter(Class<?> type);
    
}
