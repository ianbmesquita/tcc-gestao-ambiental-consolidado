package br.pucmg.sigam.monitoramento.api.dtos;

import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitanteInfoResponseDTO {
    private List<EstadoResponseDTO> estados;
    private List<BarragemResponseDTO> barragens;
}