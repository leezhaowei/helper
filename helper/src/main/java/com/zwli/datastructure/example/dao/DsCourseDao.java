package com.zwli.datastructure.example.dao;

import java.util.List;

import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.jdbc.dao.Dao;

public interface DsCourseDao extends Dao<DsCourse> {

    public List<DsCourse> listCoursesByUserId(List<Integer> idList);

    public List<DsCourse> listDsCourses(String sql, List<Integer> idList);

}
