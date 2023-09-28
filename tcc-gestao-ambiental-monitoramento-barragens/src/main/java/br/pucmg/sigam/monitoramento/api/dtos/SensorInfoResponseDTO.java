package br.pucmg.sigam.monitoramento.api.dtos;

import br.pucmg.sigam.monitoramento.application.domain.sensor.models.TipoSensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorInfoResponseDTO {
    private List<TipoSensorResponseDTO> tiposSensores;
    private List<BarragemResponseDTO> barragens;
}
