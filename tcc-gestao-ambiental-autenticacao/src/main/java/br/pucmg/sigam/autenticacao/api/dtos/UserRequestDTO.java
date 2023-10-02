package br.pucmg.sigam.autenticacao.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String login;

    private String password;

    @NotBlank
    private String role;

    @NotNull
    private Boolean locked;
}
