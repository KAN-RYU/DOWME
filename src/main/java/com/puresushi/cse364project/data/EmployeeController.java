package com.puresushi.cse364project.data;

import com.puresushi.cse364project.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.*;

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

    // Get One items
    @GetMapping("/employees/{id}")
    Employee oneEmployee(@PathVariable Long id) {
        return db.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // Add new item
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return db.save(newEmployee);
    }

}
