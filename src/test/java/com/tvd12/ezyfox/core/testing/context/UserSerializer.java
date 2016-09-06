/**
 * 
 */
package com.tvd12.ezyfox.core.testing.context;

import com.tvd12.ezyfox.core.serialize.ObjectSerializer;
import com.tvd12.ezyfox.core.transport.Parameters;
import com.tvd12.ezyfox.core.transport.impl.ParameterWrapper;

/**
 * @author tavandung12
 *
 */
public class UserSerializer implements ObjectSerializer {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectSerializer#serialize(java.lang.Object)
     */
    @Override
    public Parameters serialize(Object object) {
        AppUser user = (AppUser)object;
        Parameters answer = new ParameterWrapper();
        answer.set("name", user.getName());
        return answer;
    }

}
