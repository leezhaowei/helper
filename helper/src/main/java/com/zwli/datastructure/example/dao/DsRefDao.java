package com.zwli.datastructure.example.dao;

import java.util.List;

import com.zwli.datastructure.example.model.DsRef;
import com.zwli.datastructure.example.model.DsRefSociality;
import com.zwli.datastructure.example.model.DsRefStucource;
import com.zwli.jdbc.dao.Dao;

public interface DsRefDao extends Dao<DsRef> {

    public List<DsRefStucource> listCourseIdsByUserId(Integer userId);

    public List<DsRefSociality> listFriendIdsByUserId(Integer userId);
}
