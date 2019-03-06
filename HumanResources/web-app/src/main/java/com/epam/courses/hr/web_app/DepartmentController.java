package com.epam.courses.hr.web_app;

import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService service;

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:departments";
    }

    @GetMapping(value = "/departments")
    public String departments(Model model) {
        model.addAttribute("departmentStubs", service.findAllStubs());
        return "departments";
    }

    @GetMapping(value = "/department")
    public String department(Model model) {
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "department";
    }

    @PostMapping(value = "/department")
    public String addDepartment(Department department) {
        LOGGER.debug("addDepartment({})", department);
        service.add(department);

        return "redirect:/departments";
    }

    @GetMapping(value = "/department/{id}")
    public String gotoEditDepartmentPage(@PathVariable Integer id, Model model) {
        LOGGER.debug("gotoEditDepartmentPage({}, {})", id, model);
        model.addAttribute("isNew", false);
        Department department = service.findById(id);
        model.addAttribute("department", department);

        return "department";
    }

    @PostMapping(value = "/department/{id}")
    public String updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})", department);

        service.update(department);
        return "redirect:/departments";
    }

    @GetMapping(value = "/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Integer id){
        LOGGER.debug("deleteDepartment({})", id);
        service.delete(id);
        return "redirect:/departments";
    }
}
