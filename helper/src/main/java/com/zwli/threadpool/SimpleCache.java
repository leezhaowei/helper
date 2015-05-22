package com.zwli.threadpool;

import java.util.HashMap;
import java.util.Map;

public class SimpleCache {

	private final Map<String, Object> cache = new HashMap<String, Object>();

	public Object load(String objectName) {
		// load the object somehow
		return null;
	}

	public void clearCache() {
		synchronized (cache) {
			cache.clear();
		}
	}

	public Object getObject(String objectName) {
		synchronized (cache) {
			Object o = cache.get(objectName);
			if (o == null) {
				o = load(objectName);
				cache.put(objectName, o);
			}
			return o;
		}
	}

}
