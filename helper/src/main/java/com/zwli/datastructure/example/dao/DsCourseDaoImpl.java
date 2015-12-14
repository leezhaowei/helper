package com.zwli.datastructure.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zwli.datastructure.example.model.DsCourse;
import com.zwli.jdbc.dao.Wrapper;

public class DsCourseDaoImpl implements DsCourseDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    RowMapper<DsCourse> rowMapper = new RowMapper<DsCourse>() {

        @Override
        public DsCourse mapRow(ResultSet rs, int paramInt) throws SQLException {
            DsCourse d = new DsCourse();
            d.setId(rs.getInt("id"));
            d.setLabel(rs.getString("label"));
            return d;
        }
    };

    @Override
    public List<DsCourse> listAll() {
        return jdbcTemplate.query(SqlProvider.DS_COURSE_LIST_ALL, rowMapper);
    }

    @Override
    public DsCourse findById(Wrapper id) {
        return jdbcTemplate.queryForObject(SqlProvider.DS_COURSE_BY_ID, new Object[] { id.value() }, rowMapper);
    }

    @Override
    public List<DsCourse> listCoursesByUserId(List<Integer> idList) {
        if (idList == null || idList.size() == 0) {
            return Collections.emptyList();
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SqlProvider.DS_COURSE_LIST_ALL);
        if (idList != null && idList.size() > 0) {
            sql.append(" where id in ( ");

            for (int i = 0; i < idList.size(); i++) {
                sql.append("?");
                if (i != idList.size() - 1) {
                    sql.append(",");
                }
            }
            sql.append(" )");
        }
        return jdbcTemplate.query(sql.toString(), idList.toArray(), rowMapper);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

}
