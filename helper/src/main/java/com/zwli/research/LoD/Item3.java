package com.zwli.research.LoD;

import java.util.Set;

public class Item3 {
    private final Attributes attributes;

    public Item3(final Attributes attributes) {
        this.attributes = attributes;
    }

    public boolean attributeExists(final String attributeName) {
        return attributes.attributeExists(attributeName);
    }

    public Set<String> values(final String attributeName) {
        return attributes.values(attributeName);
    }

    public String getSingleValue(final String attributeName) {
        return attributes.getSingleValue(attributeName);
    }
}
