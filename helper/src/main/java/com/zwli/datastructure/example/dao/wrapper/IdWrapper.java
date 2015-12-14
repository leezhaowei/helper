package com.zwli.datastructure.example.dao.wrapper;

import com.zwli.jdbc.dao.Wrapper;

public class IdWrapper implements Wrapper {
    private Integer value;

    public IdWrapper(Integer _value) {
        value = _value;
    }

    @Override
    public Integer value() {
        return value;
    }

}
