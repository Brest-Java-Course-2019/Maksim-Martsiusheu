package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback
class EmployeeDaoImplTest {

    private static final int EMPLOYEE_TO_DELETE_ID = 5;

    private static final String NEW_EMPLOYEE_FIRSTNAME = "New Employee Firstname";
    private static final String NEW_EMPLOYEE_LASTNAME = "New Employee Lastname";
    private static final int NEW_EMPLOYEE_DEPT_ID = 1;
    private static final int NEW_EMPLOYEE_SALARY = 300;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testFindAll() {
        Stream<Employee> employeeStream = employeeDao.findAll();
        assertNotNull(employeeStream);
    }

    @Test
    void testAddEmployee() {
        Stream<Employee> employeesBeforeTest = employeeDao.findAll();

        Employee employee = new Employee();
        employee.setFirstName("Alex");
        employee.setLastName("Fedaryc");
        employee.setSalary(new BigDecimal(500));
        employee.setDepartmentId(1);

        employeeDao.add(employee);

        Stream<Employee> employeesAfterTest = employeeDao.findAll();

        assertEquals(1, employeesAfterTest.count() - employeesBeforeTest.count());
    }

    @Test
    void testDelete() {
        employeeDao.delete(EMPLOYEE_TO_DELETE_ID);

        assertThrows(DataAccessException.class, () -> {
            employeeDao.findById(EMPLOYEE_TO_DELETE_ID);
        });
    }

    @Test
    void testUpdate() {
        Employee employee = new Employee();
        employee.setFirstName(NEW_EMPLOYEE_FIRSTNAME);
        employee.setLastName(NEW_EMPLOYEE_LASTNAME);
        employee.setSalary(new BigDecimal(NEW_EMPLOYEE_SALARY));
        employee.setDepartmentId(NEW_EMPLOYEE_DEPT_ID);

        Employee newEmployee = employeeDao.add(employee).get();
        assertNotNull(newEmployee.getEmployeeId());

        employee.setFirstName(NEW_EMPLOYEE_FIRSTNAME + "1");
        employee.setLastName(NEW_EMPLOYEE_LASTNAME + "1");

        Employee updatedEmployee = employeeDao.add(employee).get();

        assertEquals(NEW_EMPLOYEE_FIRSTNAME + "1", updatedEmployee.getFirstName());
    }


}