/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ObjectSerializerMapper;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import com.tvd12.ezyfox.core.testing.context.UserSerializer;

/**
 * @author tavandung12
 *
 */
public class ObjectSerializerMapperTest {

    @Test
    public void test() {
        UserSerializer userSerializer = new UserSerializer();
        ObjectSerializerMapper mapper = new ObjectSerializerMapper();
        mapper.add(AppUser.class, userSerializer);
        assertEquals(mapper.get(AppUser.class), userSerializer);
        mapper.remove(AppUser.class);
        mapper.clear();
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void test1() {
        ObjectSerializerMapper mapper = new ObjectSerializerMapper();
        mapper.get(AppUser.class);
    }
    
}
