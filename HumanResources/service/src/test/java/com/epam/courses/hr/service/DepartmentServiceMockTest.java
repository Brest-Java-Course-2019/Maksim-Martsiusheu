package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.DepartmentDao;
import com.epam.courses.hr.model.Department;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.stream.Stream;

public class DepartmentServiceMockTest {

    private DepartmentDao departmentDao;

    private DepartmentService departmentService;

    @BeforeEach
    void setup() {
        departmentDao = Mockito.mock(DepartmentDao.class);
        departmentService = new DepartmentServiceImpl(departmentDao);
    }

    @Test
    void testFindAll() {
        Mockito.when(departmentDao.findAll()).thenReturn(Stream.of(create()));

        Stream<Department> result = departmentService.findAll();

        assertNotNull(result);
        assertEquals(1, result.count());

        Mockito.verify(departmentDao, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(departmentDao);

    }
    private Department create() {
        Department department = new Department();
        department.setDepartmentName("name");
        department.setDepartmentDescription("desc");
        return department;
    }
}
