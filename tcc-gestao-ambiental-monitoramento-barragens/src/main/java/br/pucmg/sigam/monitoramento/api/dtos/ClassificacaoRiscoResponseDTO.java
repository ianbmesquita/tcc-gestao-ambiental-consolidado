package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassificacaoRiscoResponseDTO {
    private String risco;
    private String valor;
}
