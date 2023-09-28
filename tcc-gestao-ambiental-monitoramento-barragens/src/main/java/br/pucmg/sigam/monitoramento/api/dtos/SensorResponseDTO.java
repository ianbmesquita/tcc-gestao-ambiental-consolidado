package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorResponseDTO {
    private Long id;
    private String nome;
    private String fabricante;
    private String tipo;
    private List<BarragemResponseDTO> barragens;
}
