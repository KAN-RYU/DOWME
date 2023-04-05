package com.puresushi.cse364project.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Employee {
    @Id
    private Long id;
    private String name;
    private String role;

    Employee() {
    }

    public Employee(String name, String role) {
        this.name = name;
        this.role = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        return Objects.equals(this.id, ((Employee) o).getId()) &&
                Objects.equals(this.name, ((Employee) o).getName()) &&
                Objects.equals(this.role, ((Employee) o).getRole());
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }
}
