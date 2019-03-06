package com.epam.courses.hr.service;

import com.epam.courses.hr.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class DepartmentServiceImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentService departmentService;

    @Test
    void findAll() {
        List<Department> departments = departmentService.findAll();
        assertNotNull(departments);
    }

    @Test
    void add() {

        long count = departmentService.findAll().size();
        LOGGER.debug("Count before: {}", count);

        Department department = create();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            departmentService.add(department, department);
        });


        long newCount = departmentService.findAll().size();
        LOGGER.debug("Count after: {}", newCount);
        assertTrue(count == newCount
        );
    }

    private Department create() {
        Department department = new Department();
        department.setDepartmentName("name");
        department.setDepartmentDescription("desc");
        return department;
    }
}