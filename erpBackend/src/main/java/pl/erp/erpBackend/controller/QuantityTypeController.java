package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.erp.erpBackend.entity.Operator;
import pl.erp.erpBackend.entity.QuantityType;
import pl.erp.erpBackend.repository.QuantityTypeRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuantityTypeController {
    private final QuantityTypeRepository quantityTypeRepository;

    @PostMapping("/quantityType")
    public QuantityType newQuantityType(@RequestBody QuantityType newQuantityType) {
        return quantityTypeRepository.save(newQuantityType);
    }

    @GetMapping("/quantityType")
    public List<QuantityType> showQuantityType() {
        return quantityTypeRepository.findAll();
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/quantityType")
    public ResponseEntity deleteQuantityType(@RequestBody Long id) {
        quantityTypeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
