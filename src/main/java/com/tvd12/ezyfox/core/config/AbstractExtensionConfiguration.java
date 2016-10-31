/**
 * 
 */
package com.tvd12.ezyfox.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tvd12.ezyfox.core.structure.RequestResponseClass;

import lombok.Getter;

/**
 * @author tavandung12
 *
 */
public class AbstractExtensionConfiguration extends BaseExtensionConfiguration {
    
    // the map of request classes and their structure
    @Getter
    protected List<RequestResponseClass> requestResponseClientStructs;
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#unmodifyAll()
     */
    @Override
    protected void unmodifyAll() {
        super.unmodifyAll();
        this.requestResponseClientStructs = Collections.unmodifiableList(requestResponseClientStructs);
    }
    
    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#buildComponents()
     */
    @Override
    protected void buildComponents() {
        this.requestResponseClientStructs = createRequestResponseHandlers();
    }

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.config.BaseExtensionConfiguration#checkAll()
     */
    @Override
    protected void checkAll() {
        checkRequestResponseClasses();
    }
    
    /**
     * Check all request listener classes
     */
    protected void checkRequestResponseClasses() {
        for(RequestResponseClass clazz : requestResponseClientStructs)
            checkExecuteMethod(clazz);
    }
    
    protected List<RequestResponseClass> createRequestResponseHandlers() {
        List<RequestResponseClass> answer = new ArrayList<>();
        for(Class<?> clazz : requestResponseClientClasses)
            answer.add(newAndInitRequestResponseClass(clazz));
        return answer;
    }
    
    protected RequestResponseClass newRequestResponseClass() {
        return new RequestResponseClass();
    }
    
    private RequestResponseClass newAndInitRequestResponseClass(Class<?> clazz) {
        RequestResponseClass answer = newRequestResponseClass();
        answer.init(clazz);
        return answer;
    }
    
    protected void checkExecuteMethod(RequestResponseClass clazz) {
        clazz.checkExecuteMethod(null, null);
    }
    
}
