package com.puresushi.cse364project.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeDB extends MongoRepository<Employee, Long> {
}
