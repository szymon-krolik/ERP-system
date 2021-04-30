package sample.dto;

import lombok.Data;

//Get data from backend
@Data
public class OperatorAuthenticationResultDto {

    private Long idOperato;
    private String firstName;
    private String lastName;
    private boolean authenticated;
}
