package com.puresushi.cse364project.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeDB db;
    EmployeeController(EmployeeDB db) {
        this.db = db;
    }

    // Get all items
    @GetMapping("/employees")
    List<Employee> allEmployees() {
        return db.findAll();
    }

    // Add new item
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return db.save(newEmployee);
    }

}
