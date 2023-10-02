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
public class IncidenteInfoResponse {
    private List<BarragemResponseDTO> barragens;
    private List<ClassificacaoRiscoResponseDTO> riscos;
    private List<TipoIncidenteResponseDTO> incidentes;
    private List<TipoOrigemResponseDTO> origens;
}
