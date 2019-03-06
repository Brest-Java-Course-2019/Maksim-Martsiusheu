package com.epam.courses.hr.rest_app;

import com.epam.courses.hr.model.Department;
import com.epam.courses.hr.service.DepartmentService;
import com.epam.courses.hr.stub.DepartmentStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
class DepartmentRestControllerTest {

    @Autowired
    private DepartmentRestController controller;

    @Autowired
    private DepartmentService departmentService;

    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void tearDown(){
        Mockito.reset(departmentService);
    }


    @Test
    void shouldReturnAllDepartments() throws Exception {
        Mockito.when(departmentService.findAll()).thenReturn(new ArrayList<Department>(){{add(createDepartment(0));add(createDepartment(1));}});

        mockMvc.perform(
                MockMvcRequestBuilders.get("/departments/all")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is("dep0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentDescription", Matchers.is("desc0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is("dep1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentDescription", Matchers.is("desc1")))
                ;

        Mockito.verify(departmentService, Mockito.times(1)).findAll();
    }
    @Test
    void shouldShowAllStubs() throws Exception{
        Mockito.when(departmentService.findAllStubs()).thenReturn(new ArrayList<DepartmentStub>(){{add(createStub(0));add(createStub(1));}});

        mockMvc.perform(
                MockMvcRequestBuilders.get("/departments/stubs")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("dep0")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].avgSalary", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("dep1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].avgSalary", Matchers.is(1)))
                ;

        Mockito.verify(departmentService, Mockito.times(1)).findAllStubs();
    }

    @Test
    void shouldAddDepartment() throws Exception{
        Department department = createDepartment(null);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/departments/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(department))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(departmentService, Mockito.times(1)).add(Mockito.any());
    }

    @Test
    void shouldUpdateDepartment() throws Exception{
        Department department = createDepartment(1);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/departments")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(MAPPER.writeValueAsString(department))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(departmentService, Mockito.times(1)).update(Mockito.any(Department.class));
    }

    @Test
    void shouldDeleteDepartment() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/departments")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(MAPPER.writeValueAsString(Integer.valueOf(1)))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(departmentService, Mockito.times(1)).delete(Mockito.anyInt());
    }

    private Department createDepartment(Integer id){
        Department department = new Department();

        department.setDepartmentId(id);
        department.setDepartmentName("dep"+id);
        department.setDepartmentDescription("desc"+id);

        return department;
    }

    private DepartmentStub createStub(Integer id){
        DepartmentStub stub = new DepartmentStub();

        stub.setId(id);
        stub.setName("dep"+id);
        stub.setAvgSalary(id);

        return stub;
    }
}