package br.pucmg.sigam.monitoramento.application.domain.incidente.services;

import br.pucmg.sigam.monitoramento.api.dtos.IncidenteInfoResponse;
import br.pucmg.sigam.monitoramento.api.dtos.IncidenteRequestDTO;
import br.pucmg.sigam.monitoramento.api.dtos.IncidenteResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.incidente.mappers.IncidenteMapper;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.BarragemRepository;
import br.pucmg.sigam.monitoramento.infra.dataproviders.repositories.IncidenteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.pucmg.sigam.monitoramento.utils.Messages.BARRAGEM_NAO_ENCONTRADA;

@Service
public class IncidenteService {
    @Autowired
    private IncidenteRepository incidenteRepository;

    @Autowired
    private BarragemRepository barragemRepository;

    @Autowired
    private IncidenteMapper mapper;

    @Autowired
    private RabbitMQService rabbitMQService;

    public List<IncidenteResponseDTO> getAllIncidentes() {
        return mapper.convertListIncidenteEntityToListIncidenteResponseDTO(incidenteRepository.findAll());
    }

    public IncidenteResponseDTO saveNewIncidente(final IncidenteRequestDTO requestDTO) {
        var barragem = barragemRepository.findById(requestDTO.getIdBarragem())
                .orElseThrow(() -> new EntityNotFoundException(String.format(BARRAGEM_NAO_ENCONTRADA, requestDTO.getIdBarragem())));

        var incidente = incidenteRepository.save(mapper.convertIncidenteRequestDTOToIncidenteEntity(requestDTO, barragem));

        try {
            if (ClassificacaoRisco.valueOf(requestDTO.getGrauRisco()).equals(ClassificacaoRisco.ALTO)) {
                rabbitMQService.sendMessage(mapper.convertIncidenteToMensagemIncidente(incidente));
            }

            return mapper.convertIncidenteEntityToIncidenteResponseDTO(incidente);
        } catch (AmqpException | JsonProcessingException exception) {
            throw new AmqpException("Erro ao enviar a mensagem para o tópico: ", exception.getCause());
        }
    }

    public IncidenteInfoResponse getDataFormHabitantes() {
        var barragens = barragemRepository.findAll();
        return mapper.convertDataToHabitanteInfoResponseDTO(barragens);
    }
}