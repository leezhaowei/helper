package com.zwli.datastructure.example.dao;

import java.util.List;

import com.zwli.datastructure.example.model.DsPerson;
import com.zwli.jdbc.dao.Dao;

public interface DsPersonDao extends Dao<DsPerson> {

    public List<DsPerson> listFriendsByUserId(List<Integer> idList);

    public List<DsPerson> listDsPersons(String sql, List<Integer> idList);

}
