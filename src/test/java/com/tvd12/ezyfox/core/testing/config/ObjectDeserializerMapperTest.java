/**
 * 
 */
package com.tvd12.ezyfox.core.testing.config;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.config.ObjectDeserializerMapper;
import com.tvd12.ezyfox.core.testing.context.AppUser;
import com.tvd12.ezyfox.core.testing.context.UserDeserializer;
import static org.testng.Assert.*;

/**
 * @author tavandung12
 *
 */
public class ObjectDeserializerMapperTest {

    @Test
    public void test() {
        UserDeserializer userDeserializer = new UserDeserializer();
        ObjectDeserializerMapper mapper = new ObjectDeserializerMapper();
        mapper.add(AppUser.class, userDeserializer);
        assertEquals(mapper.get(AppUser.class), userDeserializer);
        mapper.remove(AppUser.class);
        mapper.clear();
    }
    
    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void test1() {
        ObjectDeserializerMapper mapper = new ObjectDeserializerMapper();
        mapper.get(AppUser.class);
    }
    
}
