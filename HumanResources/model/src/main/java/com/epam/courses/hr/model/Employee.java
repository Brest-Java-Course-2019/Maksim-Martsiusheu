package com.epam.courses.hr.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer departmentId;
    private BigDecimal salary;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId.equals(employee.employeeId) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                departmentId.equals(employee.departmentId) &&
                salary.equals(employee.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, departmentId, salary);
    }
}
