package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.erp.erpBackend.dto.EmployeeDto;
import pl.erp.erpBackend.entity.Employee;
import pl.erp.erpBackend.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    @GetMapping("/employee")
    public  List<EmployeeDto> showEmployees() {
        return employeeRepository.findAll()
                .stream()//lista na strumien
                .map(EmployeeDto::of)//wylowyane na kazdym obiekcie listy funkcje 'OF'
                .collect(Collectors.toList());//zbieranie kazdego wyniku
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/employee")
    public ResponseEntity deleteEmployee(@RequestBody Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
