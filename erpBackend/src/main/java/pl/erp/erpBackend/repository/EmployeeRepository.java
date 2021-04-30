package pl.erp.erpBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.erp.erpBackend.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long > {


}
