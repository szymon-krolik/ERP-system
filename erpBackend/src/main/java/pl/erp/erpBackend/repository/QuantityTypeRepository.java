package pl.erp.erpBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.erp.erpBackend.entity.QuantityType;

public interface QuantityTypeRepository extends JpaRepository<QuantityType, Long> {
}
