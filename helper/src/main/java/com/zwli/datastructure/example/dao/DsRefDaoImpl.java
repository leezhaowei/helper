package com.zwli.datastructure.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.zwli.datastructure.example.model.DsRef;
import com.zwli.datastructure.example.model.DsRefSociality;
import com.zwli.datastructure.example.model.DsRefStucource;
import com.zwli.jdbc.dao.Wrapper;

public class DsRefDaoImpl implements DsRefDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private RowMapper<DsRefStucource> stucourceMapper = new RowMapper<DsRefStucource>() {

        @Override
        public DsRefStucource mapRow(ResultSet rs, int rowNum) throws SQLException {
            DsRefStucource d = new DsRefStucource();
            d.setStudentId(rs.getInt("student_id"));
            d.setCourseId(rs.getInt("course_id"));
            return d;
        }
    };
    private RowMapper<DsRefSociality> socialityMapper = new RowMapper<DsRefSociality>() {

        @Override
        public DsRefSociality mapRow(ResultSet rs, int rowNum) throws SQLException {
            DsRefSociality d = new DsRefSociality();
            d.setStudentId(rs.getInt("student_id"));
            d.setFriendId(rs.getInt("friend_id"));
            return d;
        }
    };

    @Override
    public List<DsRef> listAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DsRef findById(Wrapper id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DsRefStucource> listCourseIdsByUserId(Integer userId) {
        return jdbcTemplate.query(SqlProvider.DS_REF_STUCOURSE, new Object[] { userId }, stucourceMapper);
    }

    @Override
    public List<DsRefSociality> listFriendIdsByUserId(Integer userId) {
        return jdbcTemplate.query(SqlProvider.DS_REF_SOCIALITY, new Object[] { userId }, socialityMapper);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }
}
