/**
 * 
 */
package com.tvd12.ezyfox.core.testing.exception;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.exception.BadRequestException;
import static org.testng.Assert.*;

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
    
    @Test(expectedExceptions = {BadRequestException.class})
    public void test4() throws BadRequestException {
        BadRequestException ex = new BadRequestException(1);
        assertEquals(ex.getCode(), 1);
        throw ex;
    }
    
    @Test(expectedExceptions = {BadRequestException.class})
    public void test5() throws BadRequestException {
        throw new BadRequestException("abc", 1);
    }
    
    @Test(expectedExceptions = {BadRequestException.class})
    public void test6() throws BadRequestException {
        throw new BadRequestException("abc", 1, new Exception());
    }
    
}
