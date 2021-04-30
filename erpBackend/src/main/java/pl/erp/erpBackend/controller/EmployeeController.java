package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.erp.erpBackend.entity.Employee;
import pl.erp.erpBackend.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employee")
    List<Employee> showEmployees() {
        return employeeRepository.findAll();
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/employee")
    ResponseEntity deleteEmployee(@RequestBody Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
