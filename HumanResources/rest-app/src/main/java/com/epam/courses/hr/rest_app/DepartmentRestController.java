package com.epam.courses.hr.rest_app;

import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.service.DepartmentService;
import com.epam.courses.hr.stub.DepartmentStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentRestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentRestController.class);

    private DepartmentService departmentService;

    @Autowired
    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/all")
    public List<Department> findAll() {
        LOGGER.debug("findAll()");
        return departmentService.findAll();
    }

    @GetMapping(value = "/stubs")
    public List<DepartmentStub> findAllStubs() {
        LOGGER.debug("findAllStubs()");
        return departmentService.findAllStubs();
    }

    @GetMapping(value = "/{id}")
    public Department findById(@PathVariable Integer departmentId) {
        LOGGER.debug("findById({})", departmentId);
        return departmentService.findById(departmentId);
    }

    @PostMapping(value = "/add")
    public void add(@RequestBody Department department) {
        LOGGER.debug("add({})",department);
        departmentService.add(department);
    }

    @PutMapping
    public void update(@RequestBody Department department) {
        LOGGER.debug("update({})", department);
        departmentService.update(department);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer departmentId) {
        LOGGER.debug("delete({})", departmentId);
        departmentService.delete(departmentId);
    }
}
