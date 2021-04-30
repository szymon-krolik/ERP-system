package pl.erp.erpBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.erp.erpBackend.dto.OperatorAuthenticationResultDto;
import pl.erp.erpBackend.dto.OperatorCredentialsDto;
import pl.erp.erpBackend.entity.Operator;

import pl.erp.erpBackend.repository.OperatorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OperatorController {
    private final OperatorRepository operatorRepository;

    @PostMapping("/operator")
    public Operator newOperator(@RequestBody Operator newOperator) {
        return operatorRepository.save(newOperator);
    }

    @GetMapping("/operator")
    public List<Operator> showOperators() {
        return operatorRepository.findAll();
    }

    //TODO zabezpieczenie przed wywaleniem serwera
    @DeleteMapping("/operator")
    public ResponseEntity deleteOperator(@RequestBody Long id) {
        operatorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/verify-operator")
    public OperatorAuthenticationResultDto verifyOperator(@RequestBody OperatorCredentialsDto operatorCredentialsDto) {
        Optional<Operator> operatorOptional = operatorRepository.findByLogin(operatorCredentialsDto.getLogin());
        if (!operatorOptional.isPresent()) {
            return OperatorAuthenticationResultDto.createUnauthenticated();
        }
        Operator operator = operatorOptional.get();
        if (!operator.getPassword().equals(operatorCredentialsDto.getPassword())) {
            return OperatorAuthenticationResultDto.createUnauthenticated();
        } else {
            return  OperatorAuthenticationResultDto.of(operator);
        }

    }
}
