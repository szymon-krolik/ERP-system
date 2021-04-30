package pl.erp.erpBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.erp.erpBackend.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
