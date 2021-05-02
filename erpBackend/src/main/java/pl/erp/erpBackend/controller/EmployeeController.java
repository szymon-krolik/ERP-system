package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.erp.erpBackend.dto.EmployeeDto;
import pl.erp.erpBackend.entity.Employee;
import pl.erp.erpBackend.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employee")
    public EmployeeDto saveOrUpdateEmployee(@RequestBody EmployeeDto dto) {
        if (dto.getIdEmployee() == null) {
            return EmployeeDto.of(employeeRepository.save(Employee.of(dto)));
        } else {
            Optional<Employee> optionalEmployee = employeeRepository.findById(dto.getIdEmployee());
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.updateEmployee(dto);
                return EmployeeDto.of(employeeRepository.save(employee));
            } else {
                throw new RuntimeException("Cant find user tih given id: " + dto.getIdEmployee());
            }
        }
    }

    @GetMapping("/employee")
    public  List<EmployeeDto> showEmployees() {
        return employeeRepository.findAll()
                .stream()//lista na strumien
                .map(EmployeeDto::of)//wylowyane na kazdym obiekcie listy funkcje 'OF'
                .collect(Collectors.toList());//zbieranie kazdego wyniku
    }

    @GetMapping("/employee/{idEmployee}")
    public EmployeeDto getEmployee(@PathVariable Long idEmployee) throws InterruptedException {
        //TODO obsluga jesli nie ma pracwonika
        Thread.sleep(500);
        Optional<Employee> optionalEmployee = employeeRepository.findById(idEmployee);
            return EmployeeDto.of(optionalEmployee.get());
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/employee/{idEmployee}")
    public ResponseEntity deleteEmployee(@PathVariable Long idEmployee) {
        employeeRepository.deleteById(idEmployee);
        return ResponseEntity.ok().build();
    }

}
