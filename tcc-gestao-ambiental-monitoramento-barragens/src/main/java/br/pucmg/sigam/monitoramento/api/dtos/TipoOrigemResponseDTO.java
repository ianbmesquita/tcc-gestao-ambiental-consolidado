package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoOrigemResponseDTO {
    private String tipo;
    private String valor;
}
