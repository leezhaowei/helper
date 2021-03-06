package com.zwli.datastructure.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zwli.datastructure.example.model.DsPerson;
import com.zwli.jdbc.dao.Wrapper;

public class DsPersonDaoImpl implements DsPersonDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    RowMapper<DsPerson> rowMapper = new RowMapper<DsPerson>() {

        @Override
        public DsPerson mapRow(ResultSet rs, int paramInt) throws SQLException {
            DsPerson d = new DsPerson();
            d.setId(rs.getInt("id"));
            d.setName(rs.getString("name"));
            d.setAge(rs.getInt("age"));
            d.setCreateDate(rs.getTimestamp("create_date"));
            return d;
        }
    };

    @Override
    public List<DsPerson> listAll() {
        return jdbcTemplate.query(SqlProvider.DS_PERSON_LIST_ALL, rowMapper);
    }

    @Override
    public DsPerson findById(Wrapper id) {
        return jdbcTemplate.queryForObject(SqlProvider.DS_PERSON_BY_ID, new Object[] { id.value() }, rowMapper);
    }

    @Override
    public List<DsPerson> listFriendsByUserId(List<Integer> idList) {
        if (idList == null || idList.size() == 0) {
            return Collections.emptyList();
        }
        StringBuilder sql = new StringBuilder();
        sql.append(SqlProvider.DS_PERSON_LIST_ALL);
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
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
}
