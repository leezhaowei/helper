package com.zwli.datastructure.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.datastructure.example.model.DsPerson;
import com.zwli.datastructure.example.model.DsRefSociality;
import com.zwli.datastructure.example.model.DsRefStucource;
import com.zwli.jdbc.JdbcSource;
import com.zwli.jdbc.JdbcSource.Builder;
import com.zwli.jdbc.JdbcUtils;

public class DsHelper {

    private static enum CriteriaType {
        STR, INT;
    }

    private static class Criteria {
        CriteriaType type;
        String value;
        int index;
    }

    private static DsHelper instance;

    public static DsHelper getInstance() {
        if (instance == null) {
            synchronized (DsHelper.class) {
                if (instance == null) {
                    instance = new DsHelper();
                }
            }
        }
        return instance;
    }

    private static String DS_PERSON_LIST_ALL = "select * from ds_person";
    private static String DS_PERSON_BY_ID = DS_PERSON_LIST_ALL + " where id = ?";
    private static String DS_COURSE_LIST_ALL = "select * from ds_course";
    private static String DS_COURSE_BY_ID = DS_COURSE_LIST_ALL + " where id = ?";
    private static String DS_REF_SOCIALITY = "select * from ds_ref_sociality where student_id = ? order by student_id, friend_id";
    private static String DS_REF_STUCOURSE = "select * from ds_ref_stucourse  where student_id = ? order by student_id, course_id";

    private JdbcSource jSource;

    private DsHelper() {
    }

    public List<DsRefStucource> listCourseIdsByUserId(Integer userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = JdbcUtils.buildConnection(jSource);
                stmt = conn.prepareStatement(DS_REF_STUCOURSE);
                stmt.setInt(1, userId);
                rs = stmt.executeQuery();
                List<DsRefStucource> list = new ArrayList<DsRefStucource>();
                while (rs.next()) {
                    DsRefStucource d = new DsRefStucource();
                    d.setStudentId(rs.getInt("student_id"));
                    d.setCourseId(rs.getInt("course_id"));
                    list.add(d);
                }
                return list;
            } finally {
                JdbcUtils.close(rs);
                JdbcUtils.close(stmt);
                JdbcUtils.close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<DsRefSociality> listFriendIdsByUserId(Integer userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = JdbcUtils.buildConnection(jSource);
                stmt = conn.prepareStatement(DS_REF_SOCIALITY);
                stmt.setInt(1, userId);
                rs = stmt.executeQuery();
                List<DsRefSociality> list = new ArrayList<DsRefSociality>();
                while (rs.next()) {
                    DsRefSociality d = new DsRefSociality();
                    d.setStudentId(rs.getInt("student_id"));
                    d.setFriendId(rs.getInt("friend_id"));
                    list.add(d);
                }
                return list;
            } finally {
                JdbcUtils.close(rs);
                JdbcUtils.close(stmt);
                JdbcUtils.close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public DsPerson getDsPerson(Integer id) {
        List<Criteria> criteriaList = buildCriteriaById(id);
        List<DsPerson> list = listDsPersons(DS_PERSON_BY_ID, criteriaList);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public DsCourse getDsCourse(Integer id) {
        List<Criteria> criteriaList = buildCriteriaById(id);
        List<DsCourse> list = listDsCourses(DS_COURSE_BY_ID, criteriaList);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    private List<Criteria> buildCriteriaById(Integer id) {
        List<Criteria> criteriaList = new ArrayList<Criteria>();
        Criteria c = new Criteria();
        c.value = id.toString();
        c.index = 1;
        c.type = CriteriaType.INT;
        criteriaList.add(c);
        return criteriaList;
    }

    public List<DsPerson> listAllDsPerson() {
        return listDsPersons(DS_PERSON_LIST_ALL, null);
    }

    public List<DsPerson> listFriendsByUserId(List<Integer> idList) {
        StringBuilder sb = new StringBuilder();
        sb.append(DS_PERSON_LIST_ALL).append(" where id in ( ");
        List<Criteria> list = new ArrayList<Criteria>();
        for (int i = 0; i < idList.size(); i++) {
            sb.append("?");
            if (i != idList.size() - 1) {
                sb.append(",");
            }
            Criteria c = new Criteria();
            c.value = idList.get(i).toString();
            c.type = CriteriaType.INT;
            c.index = i + 1;
            list.add(c);
        }
        sb.append(" )");
        return listDsPersons(sb.toString(), list);
    }

    public List<DsCourse> listCoursesByUserId(List<Integer> idList) {
        StringBuilder sb = new StringBuilder();
        sb.append(DS_COURSE_LIST_ALL).append(" where id in ( ");
        List<Criteria> list = new ArrayList<Criteria>();
        for (int i = 0; i < idList.size(); i++) {
            sb.append("?");
            if (i != idList.size() - 1) {
                sb.append(",");
            }
            Criteria c = new Criteria();
            c.value = idList.get(i).toString();
            c.type = CriteriaType.INT;
            c.index = i + 1;
            list.add(c);
        }
        sb.append(" )");
        return listDsCourses(sb.toString(), list);
    }

    public List<DsPerson> listDsPersons(String sql, List<Criteria> criteriaList) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = JdbcUtils.buildConnection(jSource);
                stmt = conn.prepareStatement(sql);
                handleCriteria(criteriaList, stmt);
                rs = stmt.executeQuery();
                List<DsPerson> list = new ArrayList<DsPerson>();
                while (rs.next()) {
                    list.add(buildDsPerson(rs));
                }
                return list;
            } finally {
                JdbcUtils.close(rs);
                JdbcUtils.close(stmt);
                JdbcUtils.close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void handleCriteria(List<Criteria> criteriaList, PreparedStatement stmt) throws SQLException {
        if (criteriaList != null) {
            for (Criteria criteria : criteriaList) {
                if (criteria.type == CriteriaType.STR) {
                    stmt.setString(criteria.index, criteria.value);
                } else if (criteria.type == CriteriaType.INT) {
                    stmt.setInt(criteria.index, Integer.valueOf(criteria.value));
                }
            }
        }
    }

    public List<DsCourse> listAllDsCourse() {
        return listDsCourses(DS_COURSE_LIST_ALL, null);
    }

    public List<DsCourse> listDsCourses(String sql, List<Criteria> criteriaList) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            try {
                conn = JdbcUtils.buildConnection(jSource);
                stmt = conn.prepareStatement(sql);
                handleCriteria(criteriaList, stmt);
                rs = stmt.executeQuery();
                List<DsCourse> list = new ArrayList<DsCourse>();
                while (rs.next()) {
                    list.add(buildDsCourse(rs));
                }
                return list;
            } finally {
                JdbcUtils.close(rs);
                JdbcUtils.close(stmt);
                JdbcUtils.close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private DsCourse buildDsCourse(ResultSet rs) throws SQLException {
        DsCourse d;
        d = new DsCourse();
        d.setId(rs.getInt("id"));
        d.setLabel(rs.getString("label"));
        return d;
    }

    private DsPerson buildDsPerson(ResultSet rs) throws SQLException {
        DsPerson d = new DsPerson();
        d.setId(rs.getInt("id"));
        d.setName(rs.getString("name"));
        d.setAge(rs.getInt("age"));
        d.setCreateDate(rs.getTimestamp("create_date"));
        return d;
    }

    public JdbcSource getjSource() {
        return jSource;
    }

    public void setjSource(JdbcSource jSource) {
        this.jSource = jSource;
    }

    public static void main(String[] args) {
        init();

        // testListAllDsPerson();
        // testGetDsPerson();
        // testListAllDsCourse();
        // testGetDsCourse();
        // testListFriendIdsByUserId();
        // testListCourseIdsByUserId();
        // testListFriendsByUserId();
        testListCoursesByUserId();
    }

    static void testListCoursesByUserId() {
        List<Integer> idList = Arrays.asList(new Integer[] { 1, 2, 3 });
        List<DsCourse> list = getInstance().listCoursesByUserId(idList);
        for (DsCourse e : list) {
            System.out.println(e);
        }
    }

    static void testListFriendsByUserId() {
        List<Integer> idList = Arrays.asList(new Integer[] { 1, 2, 3 });
        List<DsPerson> list = getInstance().listFriendsByUserId(idList);
        for (DsPerson e : list) {
            System.out.println(e);
        }
    }

    static void testListCourseIdsByUserId() {
        List<DsRefStucource> list = getInstance().listCourseIdsByUserId(1);
        for (DsRefStucource d : list) {
            System.out.println(d);
        }
    }

    static void testListFriendIdsByUserId() {
        List<DsRefSociality> list = getInstance().listFriendIdsByUserId(1);
        for (DsRefSociality d : list) {
            System.out.println(d);
        }
    }

    static void testGetDsPerson() {
        DsPerson d = getInstance().getDsPerson(1);
        System.out.println(d);
    }

    static void testGetDsCourse() {
        DsCourse d = getInstance().getDsCourse(1);
        System.out.println(d);
    }

    static void testListAllDsPerson() {
        List<DsPerson> list = getInstance().listAllDsPerson();
        for (DsPerson e : list) {
            System.out.println(e);
        }
    }

    static void testListAllDsCourse() {
        List<DsCourse> list = getInstance().listAllDsCourse();
        for (DsCourse e : list) {
            System.out.println(e);
        }
    }

    private static void init() {
        Builder builder = new Builder();
        JdbcSource j = builder.url("jdbc:mysql://localhost:3306/ds").driver("com.mysql.jdbc.Driver").username("admin")
                .password("admin").build();
        getInstance().setjSource(j);
    }
}
