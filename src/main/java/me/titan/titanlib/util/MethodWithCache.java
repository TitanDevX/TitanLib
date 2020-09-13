package me.titan.titanlib.util;

public class MethodWithCache<T> {

	final T returnObj;
	final Object[] cache;

	public MethodWithCache(T returnObj, Object... cache) {
		this.returnObj = returnObj;
		this.cache = cache;
	}


	public T getReturnObj() {
		return returnObj;
	}

	public Object[] getCache() {
		return cache;
	}
}
