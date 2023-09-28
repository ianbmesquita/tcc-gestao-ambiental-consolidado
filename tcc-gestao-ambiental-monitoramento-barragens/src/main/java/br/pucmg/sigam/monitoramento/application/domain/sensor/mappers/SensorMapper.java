package br.pucmg.sigam.monitoramento.application.domain.sensor.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.*;
import br.pucmg.sigam.monitoramento.application.domain.barragem.mappers.BarragemMapper;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.TipoOrigem;
import br.pucmg.sigam.monitoramento.application.domain.sensor.models.Sensor;
import br.pucmg.sigam.monitoramento.application.domain.sensor.models.TipoSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SensorMapper {
    @Autowired
    private BarragemMapper barragemMapper;

    public SensorInfoResponseDTO convertDataToSensorInfoResponseDTO(final List<Barragem> barragens) {
        return SensorInfoResponseDTO.builder()
                .tiposSensores(Arrays.asList(TipoSensor.values()).stream().map(sensor -> {
                            return TipoSensorResponseDTO.builder()
                                    .valor(sensor.name())
                                    .tipo(sensor.getTipo())
                                    .build();
                }).toList())
                .barragens(barragemMapper.convertListBarragemEntityToListBarragemResponseDTO(barragens))
                .build();
    }

    public Sensor convertSensorRequestDTOToSensorEntity(final SensorRequestDTO requestDTO, Barragem barragem) {
        return Sensor.builder()
                .nome(requestDTO.getNome())
                .fabricante(requestDTO.getFabricante())
                .tipo(TipoSensor.valueOf(requestDTO.getTipo()))
                .barragens(List.of(barragem))
                .build();
    }

    public SensorResponseDTO convertSensorEntityToSensorResponseDTO(final Sensor sensor) {
        return SensorResponseDTO.builder()
                .id(sensor.getId())
                .nome(sensor.getNome())
                .fabricante(sensor.getFabricante())
                .tipo(sensor.getTipo().getTipo())
                .barragens(barragemMapper.convertListBarragemEntityToListBarragemResponseDTO(sensor.getBarragens()))
                .build();
    }

    public List<SensorResponseDTO> convertListSensorEntityToListSensorResponseDTO(final List<Sensor> sensores) {
        List sensoresResponseDTO = new ArrayList();

        for (Sensor sensor : sensores) {
            sensoresResponseDTO.add(convertSensorEntityToSensorResponseDTO(sensor));
        }

        return sensoresResponseDTO;
    }

    public IncidenteRequestDTO convertLeituraSensorRequestDTOToIncidenteRequestDTO(final LeituraSensorRequestDTO requestDTO,
                                                                                   final Long id) {
        return IncidenteRequestDTO.builder()
                .idBarragem(requestDTO.getIdBarragem())
                .dataHora(requestDTO.getDataHora())
                .grauRisco(requestDTO.getGrauRisco())
                .alerta(requestDTO.getAlerta())
                .origem(TipoOrigem.SENSOR.toString())
                .observacoes("Leitura aferida pelo sensor de id: " + id)
                .build();
    }
}
