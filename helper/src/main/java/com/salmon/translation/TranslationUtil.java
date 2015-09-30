package com.salmon.translation;

import java.util.HashMap;
import java.util.Map;

public class TranslationUtil {
    private static Map<String, String> _CACHE;
    private String resource;

    public TranslationUtil(String resource) {
        super();
        this.resource = resource;
        init();
    }

    private void init() {
        if (null == _CACHE) {
            _CACHE = new HashMap<String, String>();
        }

        String[] arr1 = resource.split(";");
        for (String s : arr1) {
            String[] arr2 = s.trim().split(",");
            _CACHE.put(arr2[0].trim(), arr2[1].trim());
        }
    }

    public String translate(String input) {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(" | ");
        String[] arr = input.split(",");
        for (String k : arr) {
            if (_CACHE.containsKey(k)) {
                String v = _CACHE.get(k);
                toReturn.append(v).append(", ");
            } else {
                toReturn.append(k).append(" %% ").append(",");
            }
        }
        if (toReturn.toString().trim().length() > 0) {
            int endIndex = toReturn.toString().trim().length() - 1;
            String s = toReturn.toString().trim().substring(0, endIndex);
            return s;
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "面料,Fabric;里料,Lining;填充物,Padding;材质,Material;厚度,Thickness;柔软度,Softness;版型,Version;弹力指数,Elastic index;"
                + "长度,Length;毛重,Gross weight;主要材质,Main material;开叉,Split ends;袖长,Sleeve length;领型,Collar type;裤型,Pants type;"
                + "裤长,Outside length;腰型,Waist type;内衬,Lining";
        TranslationUtil t = new TranslationUtil(s);

        String input = "裤型,裤长,腰型,面料,内衬,厚度,柔软度,版型,弹力指数,毛重";
        String r = t.translate(input);
        System.out.println(" "+r);
    }
}
