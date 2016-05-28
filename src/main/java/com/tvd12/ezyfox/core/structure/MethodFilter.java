package com.tvd12.ezyfox.core.structure;

import java.lang.reflect.Method;

/**
 * Support to filter invalid method
 * 
 * @author tavandung12
 *
 */

public interface MethodFilter {

    /**
     * filter invalid method
     * 
     * @param method method to validate
     * @return true or false
     */
    boolean filter(Method method);
    
}
