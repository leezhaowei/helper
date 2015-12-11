package com.zwli.jdbc.dao;

import java.util.List;

import javax.sql.DataSource;

public interface Dao<T> {

    public List<T> listAll();

    public T findById(Wrapper id);

    public void setDataSource(DataSource dataSource);
}
