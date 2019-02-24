package com.epam.courses.hr.service;

import com.epam.courses.hr.dao.DepartmentDao;
import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.stub.DepartmentStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    private DepartmentDao dao;

    public DepartmentServiceImpl(DepartmentDao dao) {
        this.dao = dao;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("findAll()");
        return dao.findAll();
    }

    @Override
    public void add(Department... departments) {
        LOGGER.debug("add(). Departments amount is {}", departments.length);

        for (Department department : departments) {
            dao.add(department);
        }
    }

    @Override
    public Stream<DepartmentStub> findAllStub() {
        LOGGER.debug("findAllStubs()");
        return dao.findAllStubs();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        return dao.findById(departmentId);
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
