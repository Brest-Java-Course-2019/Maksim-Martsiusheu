package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.DepartmentDao;
import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.stub.DepartmentStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    private DepartmentDao dao;

    public DepartmentServiceImpl(DepartmentDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Department> findAll() {
        LOGGER.debug("findAll()");
        return dao.findAll().collect(Collectors.toList());
    }

    @Override
    public void add(Department... departments) {
        LOGGER.debug("add(). Departments amount is {}", departments.length);

        for (Department department : departments) {
            dao.add(department);
        }
    }

    @Override
    public List<DepartmentStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        return dao.findAllStubs().collect(Collectors.toList());
    }

    @Override
    public Department findById(Integer departmentId) {
        return dao.findById(departmentId)
                .orElseThrow(()->new RuntimeException("Failed to get department from DB"));
    }

    @Override
    public void update(Department department) {
        dao.update(department);
    }

    @Override
    public void delete(Integer departmentId) {
        dao.delete(departmentId);
    }
}
