/**
 * 
 */
package com.tvd12.ezyfox.core.testing.utilities;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.util.EzyArrays;
import com.tvd12.ezyfox.core.util.Refactor;
import com.tvd12.test.base.BaseTest;

/**
 * @author tavandung12
 *
 */
public class EzyArraysTest extends BaseTest {

    @Test
    public void test() {
        List<String> strs = Lists.newArrayList("1", "2", "3");
        Integer[] nums = EzyArrays.newArray(strs, Integer.class, new Refactor<String, Integer>() {
        	@Override
        	public Integer refact(String input) {
        		return Integer.valueOf(input);
        	}
		});
        Assert.assertEquals(nums, new Integer[] {1, 2, 3});
    }
    
    @Override
    public Class<?> getTestClass() {
    	return EzyArrays.class;
    }
    
}
