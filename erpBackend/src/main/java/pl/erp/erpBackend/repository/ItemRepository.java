package pl.erp.erpBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.erp.erpBackend.entity.Item;

public interface ItemRepository  extends JpaRepository<Item, Long> {
}
