package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Employee;
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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@PropertySource("classpath:sql-query.properties")
public class EmployeeDaoImpl implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Value("${emp.selectAll}")
    private String getAllEmployeesSql;

    @Value("${emp.selectById}")
    private String getEmployeeByIdSql;

    @Value("${emp.insert}")
    private String insertEmployeeSql;

    @Value("${emp.update}")
    private String updateEmployeeSql;

    @Value("${emp.delete}")
    private String deleteEmployeeSql;

    private static final String EMP_ID = "emp_id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String SALARY = "salary";
    private static final String DEPT_ID = "dept_id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Employee> findAll() {
        LOGGER.debug("findAll()");

        List<Employee> employeeList = namedParameterJdbcTemplate.query(getAllEmployeesSql, new EmployeeRowMapper());

        return employeeList.stream();
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        LOGGER.debug("findById({})", employeeId);

        SqlParameterSource namedParameters = new MapSqlParameterSource(EMP_ID, employeeId);

        Employee employee = namedParameterJdbcTemplate.queryForObject(getEmployeeByIdSql, namedParameters, new EmployeeRowMapper());

        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<Employee> add(Employee employee) {
        LOGGER.debug("add({})", employee);

        MapSqlParameterSource mapSqlParameterSource = getParametersSourceEmployee(employee);

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertEmployeeSql, mapSqlParameterSource, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());

        return Optional.of(employee);
    }


    @Override
    public void update(Employee employee) {
        MapSqlParameterSource mapSqlParameterSource = getParametersSourceEmployee(employee);
        Optional.of(namedParameterJdbcTemplate.update(updateEmployeeSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update employee in DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    @Override
    public void delete(Integer employeeId) {
        LOGGER.debug("delete({})", employeeId);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(EMP_ID, employeeId);
        Optional.of(namedParameterJdbcTemplate.update(deleteEmployeeSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(()->new RuntimeException("Failed to delete employee in DB"));
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(EMP_ID));
            employee.setFirstName(resultSet.getString(FIRSTNAME));
            employee.setLastName(resultSet.getString(LASTNAME));
            employee.setSalary(new BigDecimal(resultSet.getDouble(SALARY)));
            employee.setDepartmentId(resultSet.getInt(DEPT_ID));

            return employee;
        }
    }

    private MapSqlParameterSource getParametersSourceEmployee(Employee employee) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(EMP_ID, employee.getEmployeeId());
        mapSqlParameterSource.addValue(FIRSTNAME, employee.getFirstName());
        mapSqlParameterSource.addValue(LASTNAME, employee.getLastName());
        mapSqlParameterSource.addValue(SALARY, employee.getSalary());
        mapSqlParameterSource.addValue(DEPT_ID, employee.getDepartmentId());

        return mapSqlParameterSource;
    }

}
