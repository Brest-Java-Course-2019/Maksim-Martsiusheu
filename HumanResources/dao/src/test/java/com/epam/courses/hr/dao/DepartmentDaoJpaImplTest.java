package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback
class DepartmentDaoJpaImplTest {

    private static final int FIRST_DEPARTMENT_ID = 1;
    private static final int FULL_DEPARTMENT_LIST = 4;
    private static final int DEPARTMENT_TO_DELETE_ID = 4;

    private static final String NEW_DEPARTMENT_NAME = "New Department";
    private static final String NEW_DEPARTMENT_DESCRIPTION = "New Department Description";

    @Autowired
    private DepartmentDao departmentDao;


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
        Department testDepartment = new Department();
        testDepartment.setDepartmentId(1);
        testDepartment.setDepartmentName("DEV");
        testDepartment.setDepartmentDescription("Development Department");
        Department department = departmentDao.findById(FIRST_DEPARTMENT_ID).get();
        assertEquals(testDepartment, department);
    }

    @Test
    void create() {

        Stream<Department> departmentsBeforeInsert = departmentDao.findAll();

        Department department = new Department();
        department.setDepartmentName("TEST");
        department.setDepartmentDescription("TEST DESCRIPTION");
        Department newDepartment = departmentDao.add(department).get();
        assertNotNull(newDepartment.getDepartmentId());

        Stream<Department> departmentsAfterInsert = departmentDao.findAll();
        assertEquals(1, departmentsAfterInsert.count() - departmentsBeforeInsert.count());
    }

    @Test
    void createDuplicateDepartment() {

        Department department2 = new Department();
        department2.setDepartmentName("TEST 2");
        department2.setDepartmentDescription("TEST DESCRIPTION 2");
        Department newDepartment = departmentDao.add(department2).get();
        assertNotNull(newDepartment.getDepartmentId());

        Department department2dubl = new Department();
        department2dubl.setDepartmentName("TEST 2");
        department2dubl.setDepartmentDescription("TEST DESCRIPTION 2");

        assertThrows(IllegalArgumentException.class, () -> {
            departmentDao.add(department2dubl);
        });
    }

    @Test
    void update() {
        Department department = new Department();
        department.setDepartmentName(NEW_DEPARTMENT_NAME);
        department.setDepartmentDescription(NEW_DEPARTMENT_DESCRIPTION);

        Department newDepartment = departmentDao.add(department).get();
        assertNotNull(newDepartment.getDepartmentId());

        department.setDepartmentName(NEW_DEPARTMENT_NAME + "_2");
        department.setDepartmentDescription(NEW_DEPARTMENT_NAME + "_2");
        departmentDao.update(department);

        Department updatedDepartment = departmentDao.findById(department.getDepartmentId()).get();

        assertEquals(NEW_DEPARTMENT_NAME + "_2", updatedDepartment.getDepartmentName());
        assertEquals(NEW_DEPARTMENT_NAME + "_2", updatedDepartment.getDepartmentDescription());
    }

    @Test
    void delete() {
        departmentDao.delete(DEPARTMENT_TO_DELETE_ID);

        assertThrows(DataAccessException.class, () -> {
            departmentDao.findById(DEPARTMENT_TO_DELETE_ID);
        });
    }
}