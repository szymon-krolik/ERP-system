package pl.erp.erpBackend.dto;

import lombok.Data;
import pl.erp.erpBackend.entity.Employee;

@Data
public class EmployeeDto {
    private Long idEmployee;
    private String firstName;
    private String lastName;
    private String salary;

    //mapowaneie uzytkownika
    public static EmployeeDto of(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setIdEmployee(employee.getIdEmployee());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setSalary(employee.getSalary());

        return dto;
    }
}
