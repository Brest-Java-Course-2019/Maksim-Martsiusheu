package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@PropertySource("classpath:sql-query.properties")
public class DepartmentDaoJpaImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoJpaImpl.class);

    @Value("${dept.selectAll}")
    private String getAllDepartmentsSql;

    @Value("${dept.selectById}")
    private String getDepartmentById;

    @Value("${dept.getDepartmentsAmountByName}")
    private String getDepartmentsAmountByName;


    @Value("${dept.insert}")
    private String INSERT;

    @Value("${dept.update}")
    private String UPDATE;

    @Value("${dept.delete}")
    private String DELETE;

    private static final String DEPARTMENT_ID = "dept_id";
    private static final String DEPARTMENT_NAME = "dept_name";
    private static final String DEPARTMENT_DESCRIPTION = "dept_description";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoJpaImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("findAll()");

        List<Department> departmentList = namedParameterJdbcTemplate.query(getAllDepartmentsSql, new DepartmentRowMapper());

        return departmentList.stream();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("findAll({})", departmentId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(getDepartmentById, namedParameters, new DepartmentRowMapper());

        return Optional.ofNullable(department);
    }

    @Override
    public Optional<Department> add(Department department) {
        LOGGER.debug("add({})", department);

        return Optional.of(department)
                .filter(this::isNameUnique)
                .map(this::insertDepartment)
                .orElseThrow(() -> new IllegalArgumentException("Department with same name is already exists in DB"));
    }

    private boolean isNameUnique(Department department) {
        return namedParameterJdbcTemplate.queryForObject(getDepartmentsAmountByName,
                new MapSqlParameterSource(DEPARTMENT_NAME, department.getDepartmentName()),
                Integer.class) == 0;
    }

    private Optional<Department> insertDepartment(Department department) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(DEPARTMENT_NAME, department.getDepartmentName());
        mapSqlParameterSource.addValue(DEPARTMENT_DESCRIPTION, department.getDepartmentDescription());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, generatedKeyHolder);
        department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        return Optional.of(department);
    }

    @Override
    public void update(Department department) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(DEPARTMENT_ID, department.getDepartmentId());
        mapSqlParameterSource.addValue(DEPARTMENT_NAME, department.getDepartmentName());
        mapSqlParameterSource.addValue(DEPARTMENT_DESCRIPTION, department.getDepartmentDescription());

        Optional.of(namedParameterJdbcTemplate.update(UPDATE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update department in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer departmentId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(DEPARTMENT_ID, departmentId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from DB"));
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