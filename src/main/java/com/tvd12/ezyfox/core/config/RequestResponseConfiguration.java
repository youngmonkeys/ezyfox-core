/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.List;

import com.tvd12.ezyfox.core.structure.RequestResponseClass;

/**
 * @author tavandung12
 *
 */
public interface RequestResponseConfiguration {

    /**
     * @return the map of request classes and their structure
     */
    List<RequestResponseClass> getRequestResponseClientStructs();
    
}
