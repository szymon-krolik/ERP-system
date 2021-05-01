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
    public EmployeeDto newEmployee(@RequestBody EmployeeDto newEmployee) {
        return EmployeeDto.of(employeeRepository.save(Employee.of(newEmployee)));
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
    @DeleteMapping("/employee")
    public ResponseEntity deleteEmployee(@RequestBody Long id) {
        employeeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
