package pl.erp.erpBackend.dto;

import lombok.Data;
import pl.erp.erpBackend.entity.Operator;

@Data
public class OperatorAuthenticationResultDto {
    private Long idOperato;
    private String firstName;
    private String lastName;
    private boolean authenticated;

    public static OperatorAuthenticationResultDto createUnauthenticated() {
        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(false);
        return dto;
    }

    public static OperatorAuthenticationResultDto of(Operator operator) {
        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(true);
        dto.setFirstName(operator.getEmployee().getFirstName());
        dto.setLastName(operator.getEmployee().getLastName());
        dto.setIdOperato(operator.getIdOperator());

        return dto;
    }
}
