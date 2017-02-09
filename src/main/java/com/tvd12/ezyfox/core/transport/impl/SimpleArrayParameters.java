package com.tvd12.ezyfox.core.transport.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.tvd12.ezyfox.core.transport.Arraymeters;

public class SimpleArrayParameters implements Arraymeters {

	private List<Object> objects = new ArrayList<>();
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.RoArraymeters#get(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(int index) {
		return (T)objects.get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.RoArraymeters#get(int, java.lang.Class)
	 */
	@Override
	public <T> T get(int index, Class<T> type) {
		return get(index);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.RoArraymeters#size()
	 */
	@Override
	public int size() {
		return objects.size();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#add(java.lang.Object[])
	 */
	@Override
	public void add(Object... values) {
		add(Lists.newArrayList(values));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#add(java.util.Collection)
	 */
	@Override
	public void add(Collection<Object> values) {
		objects.addAll(values);
	}

	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#set(int, java.lang.Object)
	 */
	@Override
	public void set(int index, Object value) {
		objects.set(index, value);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.tvd12.ezyfox.core.transport.Arraymeters#remove(int)
	 */
	@Override
	public void remove(int index) {
		objects.remove(index);
	}

}
