package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DepartmentDaoJpaImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJpaImpl.class);

    private static final String SELECT_ALL = "select dept_id, dept_name, dept_description from dept";
    private static final String FIND_BY_ID = "select dept_id, dept_name, dept_description from dept where dept_id = :dept_id";
    private static final String DEPARTMENT_ID = "dept_id";
    private static final String DEPARTMENT_NAME = "dept_name";
    private static final String DEPARTMENT_DESCRIPTION = "dept_description";

    final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("findAll()");

        List<Department> departmentList = namedParameterJdbcTemplate.query(SELECT_ALL, new DepartmentRowMapper());

        return departmentList.stream();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("findAll({})", departmentId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new DepartmentRowMapper());

        return Optional.ofNullable(department);
    }

    @Override
    public Optional<Department> add(Department department) {
        return Optional.empty();
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {

            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDepartmentDescription(resultSet.getString(DEPARTMENT_DESCRIPTION));

            return department;
        }
    }

}