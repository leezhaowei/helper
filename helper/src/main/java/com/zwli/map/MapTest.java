package com.zwli.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MapTest {

    public static void main(String[] args) {
        testA();
    }

    static void testA() {
        Map<String, MapBean> map = new TreeMap<String, MapBean>();
        for (int i = 1; i < 10; i++) {
            MapBean e = new MapBean();
            e.setName("key" + i);
            e.setNum(i);
            map.put(e.getName(), e);
        }

        print(map);

        Random r = new Random();
        int index = r.nextInt(10);
        String key = "key" + index;
        MapBean e = map.get(key);
        e.setNum(e.getNum() * 999);

        System.out.println();
        print(map);
    }

    private static void print(Map<String, MapBean> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            MapBean e = map.get(it.next());
            System.out.println(e);
        }
    }
}
