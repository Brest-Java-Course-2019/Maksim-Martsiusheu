package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
class DepartmentDaoJpaImpTest {

    private static final int FIRST_DEPARTMENT_ID = 1;
    private static final int FULL_DEPARTMENT_LIST = 4;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private Department testDepartment;

    @Test
    void findAll() {
        Stream<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
    }

    @Test
    void findAllCheckCount() {
        Stream<Department> departments = departmentDao.findAll();
        assertEquals(FULL_DEPARTMENT_LIST, departments.count());
    }

    @Test
    void findByIdTest() {
        Department department = departmentDao.findById(FIRST_DEPARTMENT_ID).get();
        assertEquals(testDepartment, department);
    }
}