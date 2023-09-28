package br.pucmg.sigam.monitoramento.application.domain.sensor.services;

import br.pucmg.sigam.monitoramento.api.dtos.LeituraSensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorInfoResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.SensorResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.incidente.services.IncidenteService;
import br.pucmg.sigam.monitoramento.application.domain.sensor.mappers.SensorMapper;
import br.pucmg.sigam.monitoramento.application.domain.sensor.models.TipoSensor;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.pucmg.sigam.monitoramento.utils.Messages.BARRAGEM_NAO_ENCONTRADA;
import static br.pucmg.sigam.monitoramento.utils.Messages.SENSOR_NAO_ENCONTRADO;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private BarragemRepository barragemRepository;

    @Autowired
    private SensorMapper mapper;

    @Autowired
    private IncidenteService incidenteService;

    public SensorInfoResponseDTO getaAllSensorDataScreen() {
        var barragens = barragemRepository.findAll();
        return mapper.convertDataToSensorInfoResponseDTO(barragens);
    }

    public List<SensorResponseDTO> getAllSensores() {
        return mapper.convertListSensorEntityToListSensorResponseDTO(sensorRepository.findAll());
    }

    public SensorResponseDTO getSensorById(final Long id) {
        var sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(SENSOR_NAO_ENCONTRADO, id)));

        return mapper.convertSensorEntityToSensorResponseDTO(sensor);
    }

    public SensorResponseDTO saveNewSensor(final SensorRequestDTO requestDTO) {
        var barragem = barragemRepository.findById(requestDTO.getIdBarragem())
                .orElseThrow(() -> new EntityNotFoundException(String.format(SENSOR_NAO_ENCONTRADO, requestDTO.getIdBarragem())));

        var sensor = sensorRepository.save(mapper.convertSensorRequestDTOToSensorEntity(requestDTO, barragem));

        return mapper.convertSensorEntityToSensorResponseDTO(sensor);
    }

    public SensorResponseDTO editSensor(final Long id, final SensorRequestDTO requestDTO) {
        var sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(SENSOR_NAO_ENCONTRADO, id)));

        sensor.setNome(requestDTO.getNome());
        sensor.setFabricante(requestDTO.getFabricante());
        sensor.setTipo(TipoSensor.valueOf(requestDTO.getTipo()));

        var sensorAtualizado = sensorRepository.save(sensor);

        return mapper.convertSensorEntityToSensorResponseDTO(sensorAtualizado);
    }

    public void deleteSensorById(final Long id) {
        var sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(SENSOR_NAO_ENCONTRADO, id)));

        sensorRepository.delete(sensor);
    }

    public void readSensorData(final LeituraSensorRequestDTO requestDTO, final Long id) {
        if (ClassificacaoRisco.valueOf(requestDTO.getGrauRisco()).equals(ClassificacaoRisco.ALTO)) {
            incidenteService.saveNewIncidente(mapper.convertLeituraSensorRequestDTOToIncidenteRequestDTO(requestDTO, id));
        }
    }
}
