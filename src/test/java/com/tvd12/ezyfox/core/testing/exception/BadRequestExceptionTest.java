/**
 * 
 */
package com.tvd12.ezyfox.core.testing.exception;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.BadRequestException;

/**
 * @author tavandung12
 *
 */
public class BadRequestExceptionTest {

    @Test(expectedExceptions = {BadRequestException.class})
    public void test1() throws BadRequestException {
        throw new BadRequestException();
    }
    
    @Test(expectedExceptions = {BadRequestException.class})
    public void test2() throws BadRequestException {
        throw new BadRequestException("abc");
    }
    
    @Test(expectedExceptions = {BadRequestException.class})
    public void test3() throws BadRequestException {
        throw new BadRequestException("abc", new Exception());
    }
    
}
