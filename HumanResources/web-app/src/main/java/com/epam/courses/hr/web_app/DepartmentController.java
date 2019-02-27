package com.epam.courses.hr.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DepartmentController {

    @GetMapping(value = "/")
    public String defaultPAgeRedirect() {
        return "redirect:departments";
    }

    @GetMapping(value = "/departments")
    public String departments(Model model) {
        return "departments";
    }

    @GetMapping(value = "/department")
    public String department(Model model) {
        return "department";
    }
}
