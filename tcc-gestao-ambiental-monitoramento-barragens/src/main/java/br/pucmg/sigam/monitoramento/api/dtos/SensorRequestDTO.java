package br.pucmg.sigam.monitoramento.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String fabricante;

    @NotBlank
    private String tipo;

    @NotNull
    @Positive
    private Long idBarragem;
}
