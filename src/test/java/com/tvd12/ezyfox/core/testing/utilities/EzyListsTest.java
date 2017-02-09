package com.tvd12.ezyfox.core.testing.utilities;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.util.EzyLists;
import com.tvd12.ezyfox.core.util.ItemFilter;
import com.tvd12.ezyfox.core.util.ItemMeasurer;
import com.tvd12.ezyfox.core.util.MapRefactor;
import com.tvd12.ezyfox.core.util.Refactor;
import com.tvd12.test.base.BaseTest;
import static org.testng.Assert.*;

public class EzyListsTest extends BaseTest {

	@Test
	public void combineTest() {
		Collection<String> a = Lists.newArrayList("a");
		Collection<String> b = Lists.newArrayList("b");
		@SuppressWarnings("unchecked")
		List<String> ab = EzyLists.combine(a, b);
		assertEquals(ab, Lists.newArrayList("a", "b"));
	}
	
	@Test
	public void newArrayList1Test() {
		Collection<String> abc = Lists.newArrayList("a", "b", "c");
		List<String> ab = EzyLists.newArrayList(abc, new ItemFilter<String>() {
			@Override
			public boolean filter(String t) {
				return "c".equals(t);
			}
		});
		assertEquals(ab, Lists.newArrayList("c"));
	}
	
	@Test
	public void newArrayList2Test() {
		Collection<String> abc = Lists.newArrayList("a", "b", "c");
		List<String> ab = EzyLists.newArrayList(abc, Lists.newArrayList("c"));
		assertEquals(ab, Lists.newArrayList("a", "b"));
	}
	
	@Test
	public void newArrayList3Test() {
		Collection<String> abc = Lists.newArrayList("a", "b", "c");
		List<String> ab = EzyLists.newArrayList(abc, "c");
		assertEquals(ab, Lists.newArrayList("a", "b"));
	}
	
	@Test
	public void newArrayList4Test() {
		Integer[] nums = new Integer[] {1, 2, 3};
		List<String> ab = EzyLists.newArrayList(nums, new Refactor<Integer, String>() {
			@Override
			public String refact(Integer input) {
				return String.valueOf(input);
			}
		});
		assertEquals(ab, Lists.newArrayList("1", "2", "3"));
	}
	
	@Test
	public void newArrayListByTransformTest() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		List<String> ab = EzyLists.newArrayListByTransform(map, 
				new MapRefactor<String, String, String>() {
			@Override
			public String refact(String key, String value) {
				return value;
			}
		});
		assertTrue(ab.containsAll(Lists.newArrayList("1", "2", "3")));
	}
	
	@Test
	public void addElementsToNewListTest() {
		List<String> abc = Lists.newArrayList("a");
		abc = EzyLists.addElementsToNewList(abc, "b", "c");
		assertEquals(abc, Lists.newArrayList("a", "b", "c"));
	}
	
	@Test
	public void getItemCountTest() {
		List<String> abc = Lists.newArrayList("a", "b", "c");
		int count = EzyLists.getItemCount(abc, new ItemFilter<String>() {
			@Override
			public boolean filter(String t) {
				return "c".equals(t);
			}
		});
		assertEquals(count, 1);
	}
	
	@Test
	public void measureItemCountTest() {
		List<String> abc = Lists.newArrayList("a", "b", "c");
		int count = EzyLists.measureItemCount(abc, new ItemMeasurer<String>() {
			@Override
			public int measure(String t) {
				return 2; 
			}
		});
		assertEquals(count, 6);
	}
	
	@Test
	public void getItemTest() {
		List<String> abc = Lists.newArrayList("a", "b", "c");
		String answer = EzyLists.getItem(abc, new ItemFilter<String>() {
			@Override
			public boolean filter(String t) {
				return "c".equals(t);
			}
		});
		assertEquals(answer, "c");
	}
	
	@Test
	public void getItemTest2() {
		List<String> abc = Lists.newArrayList("a", "b", "c");
		String answer = EzyLists.getItem(abc, new ItemFilter<String>() {
			@Override
			public boolean filter(String t) {
				return "d".equals(t);
			}
		});
		assertEquals(answer, null);
	}
	
	@Override
	public Class<?> getTestClass() {
		return EzyLists.class;
	}
	
}
