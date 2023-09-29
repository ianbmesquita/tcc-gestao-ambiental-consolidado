package br.pucmg.sigam.monitoramento.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO {
    @NotBlank
    @Pattern(
            regexp = "^\\d{8}$",
            message = "CEP inválido. Utilize um CEP com 8 dígitos numéricos."
    )
    private String cep;

    @NotBlank
    private String logradouro;

    @NotNull
    private Integer numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String municipio;

    @NotBlank
    private String estado;
}
