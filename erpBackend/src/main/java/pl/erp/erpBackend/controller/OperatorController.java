package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.erp.erpBackend.entity.Operator;

import pl.erp.erpBackend.repository.OperatorRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OperatorController {
    private final OperatorRepository operatorRepository;

    @PostMapping("/operator")
    Operator newOperator(@RequestBody Operator newOperator) {
        return operatorRepository.save(newOperator);
    }

    @GetMapping("/operator")
    List<Operator> showOperators() {
        return operatorRepository.findAll();
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/operator")
    ResponseEntity deleteOperator(@RequestBody Long id) {
        operatorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
