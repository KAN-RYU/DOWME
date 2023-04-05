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
    Employee oneEmployee(@PathVariable String id) {
        return db.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    // Add new item
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return db.save(newEmployee);
    }

    // Update existed item
    @PutMapping("/employees/{id}")
    Employee updateEmployee(@RequestBody Employee newEmployee,@PathVariable String id) {
        return db.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return db.save(employee);
        }).orElseGet(() -> {
            newEmployee.setId(id);
            return db.save(newEmployee);
        });
    }

}
