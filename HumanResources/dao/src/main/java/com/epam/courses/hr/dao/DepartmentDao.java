package com.epam.courses.hr.dao;

import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.stub.DepartmentStub;

import java.util.Optional;
import java.util.stream.Stream;

public interface DepartmentDao {

    Stream<Department> findAll();

    Stream<DepartmentStub> findAllStubs();

    Optional<Department> findById(Integer departmentId);

    Optional<Department> add(Department department);

    void update(Department department);

    void delete(Integer departmentId);


}
