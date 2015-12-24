package com.zwli.research.LoD;

import java.util.Map;
import java.util.Set;

public class Item1 {
    private final Map<String, Set<String>> attributes;

    public Item1(final Map<String, Set<String>> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Set<String>> getAttributes() {
        return attributes;
    }
}
