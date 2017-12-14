package net.lzw.acountbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class ItemConverter {

	public static void convertCZ2(int idx) throws Exception {
		String fn = "D:/me/account/final.items_CZ2.txt";
		BufferedReader br = null;
		StringBuilder info = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(new File(fn)));
			while (br.ready()) {
				String t = br.readLine();
				if (!StringUtils.isEmpty(t)) {
					t = t.replace("【", "").replace("】", ",");
					String[] arr = t.split(",");
					if (arr.length == 2) {
						info.append(arr[idx]).append("\n");
					}
				}
			}
			System.out.println(info);
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	public static void convertCZ() throws Exception {
		String fn = "D:/me/account/final.items_CZ1.txt";
		Map<String, Set<String>> map = new TreeMap<>();
		BufferedReader br = null;
		Set<String> set = new HashSet<>();
		try {
			br = new BufferedReader(new FileReader(new File(fn)));
			while (br.ready()) {
				String t = br.readLine();
				if (!StringUtils.isEmpty(t)) {
					t = t.replace("【", "").replace("】", ",");
					String[] arr = t.split(",");
					if (arr.length == 2) {
						convertCZ(arr, map);
					} else {
						set.add(t);
					}
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}

		print(map);
		System.out.println("==================================");
		System.out.println(set);
	}

	private static void print(Map<String, Set<String>> map) {
		Set<String> keys = map.keySet();
		for (String k : keys) {
			Set<String> values = map.get(k);
			System.out.println(k + " : " + values);
		}
	}

	private static void convertCZ(String[] arr, Map<String, Set<String>> map) {
		String key = arr[0];
		Set<String> values = null;
		if (map.containsKey(key)) {
			values = map.get(key);
			values.add(arr[1]);
		} else {
			values = new TreeSet<>();
			values.add(arr[1]);
			map.put(key, values);
		}
	}

	public static void convertSSJ() throws Exception {
		String fn = "D:/me/account/items_SSJ.txt";
		Map<String, Set<String>> map = new TreeMap<>();
		BufferedReader br = null;
		StringBuilder info = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(new File(fn)));
			while (br.ready()) {
				String t = br.readLine();
				if (!StringUtils.isEmpty(t)) {
					info.append(t);
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		String[] arr0 = info.toString().split(";");
		for (String t0 : arr0) {
			String[] arr1 = t0.split(":");
			String[] arr2 = arr1[1].split(",");
			Set<String> set = new TreeSet<>();
			for (String t1 : arr2) {
				set.add(t1);
			}
			map.put(arr1[0], set);
		}

		print(map);
	}

	public static void main(String[] args) throws Exception {
		int idx;
		idx = 0;
		idx = 1;
		convertCZ2(idx);

		// convertCZ();
		// convertSSJ();
	}

}
