package com.epam.courses.hr.model;

import java.util.Objects;

public class Department {

    private Integer departmentId;
    private String departmentName;
    private String departmentDescription;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        this.departmentDescription = departmentDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return departmentId.equals(that.departmentId) &&
                departmentName.equals(that.departmentName) &&
                departmentDescription.equals(that.departmentDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, departmentName, departmentDescription);
    }
}
