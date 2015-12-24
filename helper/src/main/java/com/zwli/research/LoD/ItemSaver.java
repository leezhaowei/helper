package com.zwli.research.LoD;

import java.util.Set;

public class ItemSaver {
    private final String valueToSave;

    public ItemSaver(final String valueToSave) {
        this.valueToSave = valueToSave;
    }

    public void doSomething(final String attributeName, final Item1 item) {
        Set<String> attributeValues = item.getAttributes().get(attributeName);
        for (String value : attributeValues) {
            if (value.equals(valueToSave)) {
                doSomethingElse();
            }
        }
    }

    private void doSomethingElse() {
    }

}
