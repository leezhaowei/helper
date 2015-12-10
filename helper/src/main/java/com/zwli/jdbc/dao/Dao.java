package com.zwli.jdbc.dao;

import java.util.List;

public interface Dao<T> {

    public List<T> listAll();

    public T findById(Wrapper id);
}
