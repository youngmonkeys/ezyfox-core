package com.tvd12.ezyfox.core.testing.transport;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.core.transport.impl.SimpleArrayParameters;
import static org.testng.Assert.*;

public class SimpleArrayParametersTest {

	@Test
	public void test() {
		SimpleArrayParameters array = new SimpleArrayParameters();
		array.add("1", "2", 3);
		assertEquals(array.get(0, String.class), "1");
		assertEquals(array.get(1, String.class), "2");
		assertEquals(array.get(2, Integer.class), new Integer(3));
		array.set(0, "a");
		assertEquals(array.get(0, String.class), "a");
		assertEquals(array.size(), 3);
		array.remove(0);
		assertEquals(array.get(0), "2");
		assertEquals(array.size(), 2);
	}
	
}
