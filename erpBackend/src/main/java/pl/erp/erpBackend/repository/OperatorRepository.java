package pl.erp.erpBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.erp.erpBackend.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
}
