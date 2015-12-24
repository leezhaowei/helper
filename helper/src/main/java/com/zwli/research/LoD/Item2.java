package com.zwli.research.LoD;

import java.util.Map;
import java.util.Set;

public class Item2 {
    private final Map<String, Set<String>> attributes;

    public Item2(final Map<String, Set<String>> attributes) {
        this.attributes = attributes;
    }

    public boolean attributeExists(final String attributeName) {
        return attributes.containsKey(attributeName);
    }

    public Set<String> values(final String attributeName) {
        return attributes.get(attributeName);
    }

    public String getSingleValue(final String attributeName) {
        return values(attributeName).iterator().next();
    }

}
