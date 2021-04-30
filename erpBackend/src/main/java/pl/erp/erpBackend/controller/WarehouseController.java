package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.erp.erpBackend.entity.Warehouse;
import pl.erp.erpBackend.repository.WarehouseRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseRepository warehouseRepository;

    @PostMapping("/warehouse")
    public Warehouse newWarehouse(@RequestBody Warehouse newWarehouse) {
        return warehouseRepository.save(newWarehouse);
    }

    @GetMapping("/warehouse")
    public List<Warehouse> showWarehouses() {
        return warehouseRepository.findAll();
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/warehouse")
    public ResponseEntity deleteQuantityType(@RequestBody Long id) {
        warehouseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
