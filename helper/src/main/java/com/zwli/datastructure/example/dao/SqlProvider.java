package com.zwli.datastructure.example.dao;

public class SqlProvider {

    public static String DS_PERSON_LIST_ALL = "select * from ds_person";
    public static String DS_PERSON_BY_ID = DS_PERSON_LIST_ALL + " where id = ?";

    public static String DS_COURSE_LIST_ALL = "select * from ds_course";
    public static String DS_COURSE_BY_ID = DS_COURSE_LIST_ALL + " where id = ?";

    public static String DS_REF_SOCIALITY = "select * from ds_ref_sociality where student_id = ? order by student_id, friend_id";
    public static String DS_REF_STUCOURSE = "select * from ds_ref_stucourse  where student_id = ? order by student_id, course_id";

}
