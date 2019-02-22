package com.epam.courses.hr.service;

import com.epam.courses.hr.model.Department;

import java.util.stream.Stream;

public interface DepartmentService {
    Stream<Department> findAll();

    void add(Department... departments);
}
