/**
 * 
 */
package com.tvd12.ezyfox.core.testing.context;

import com.tvd12.ezyfox.core.serialize.ObjectDeserializer;
import com.tvd12.ezyfox.core.transport.Parameters;

/**
 * @author tavandung12
 *
 */
public class UserDeserializer implements ObjectDeserializer<AppUser> {

    /* (non-Javadoc)
     * @see com.tvd12.ezyfox.core.serialize.ObjectDeserializer#deserialize(com.tvd12.ezyfox.core.transport.Parameters)
     */
    @Override
    public AppUser deserialize(AppUser object, Parameters params) {
        AppUser user = new AppUser();
        user.setName(params.get("name", String.class));
        return user;
    }

}
