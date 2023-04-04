package com.puresushi.cse364project.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDB extends JpaRepository<Employee, Long> {
}
