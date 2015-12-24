package com.zwli.research.LoD;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Attributes {
    private final Map<String, Set<String>> attributes;

    public Attributes() {
        this.attributes = new HashMap<>();
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

    public Attributes addAttribute(final String attributeName, final Collection<String> values) {
        this.attributes.put(attributeName, new HashSet<>(values));
        return this;
    }
}
