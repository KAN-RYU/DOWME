package com.puresushi.cse364project.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDB extends JpaRepository<Employee, Long> {
}
