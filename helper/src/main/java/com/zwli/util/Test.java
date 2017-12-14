package com.zwli.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println();

			int entries = i * 100 * 1000;
			long t0 = 0; // test(entries, new FakeMap());
			long t1 = test(entries, new HashMap());
			long t2 = test(entries, new ConcurrentHashMap());

			long diff = (t2 - t1) * 100 / (t1 - t0);
			System.out.printf("entries=%,d time diff= %d%% %n", entries, diff);
		}
	}

	static long test(int ENTRIES, Map map) {
		long SEED = 0;
		Random random = new Random(SEED);

		int RW_RATIO = 10;

		long t0 = System.nanoTime();

		for (int i = 0; i < ENTRIES; i++) {
			map.put(random.nextInt(), random.nextInt());
		}

		for (int i = 0; i < RW_RATIO; i++) {
			random.setSeed(SEED);
			for (int j = 0; j < ENTRIES; j++) {
				map.get(random.nextInt());
				random.nextInt();
			}
		}
		long t = System.nanoTime() - t0;
		System.out.printf("%,d ns %s %n", t, map.getClass());
		return t;
	}

	static class FakeMap implements Map {
		@Override
		public Object get(Object key) {
			return null;
		}

		@Override
		public Object put(Object key, Object value) {
			return null;
		}

		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean containsKey(Object key) {
			return false;
		}

		@Override
		public boolean containsValue(Object value) {
			return false;
		}

		@Override
		public Object remove(Object key) {
			return null;
		}

		@Override
		public void putAll(Map m) {
		}

		@Override
		public void clear() {
		}

		@Override
		public Set keySet() {
			return null;
		}

		@Override
		public Collection values() {
			return null;
		}

		@Override
		public Set entrySet() {
			return null;
		}
	}

}
