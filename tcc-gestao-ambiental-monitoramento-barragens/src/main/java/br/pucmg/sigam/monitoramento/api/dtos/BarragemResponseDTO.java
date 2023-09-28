package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarragemResponseDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String municipio;
    private String estado;
    private String risco;
    private String status;
    private Long latitude;
    private Long longitude;
}
