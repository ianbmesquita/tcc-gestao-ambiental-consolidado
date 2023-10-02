package br.pucmg.sigam.monitoramento.application.domain.incidente.mappers;

import br.pucmg.sigam.monitoramento.api.dtos.*;
import br.pucmg.sigam.monitoramento.application.domain.barragem.mappers.BarragemMapper;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.Incidente;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.MensagemIncidente;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.TipoIncidente;
import br.pucmg.sigam.monitoramento.application.domain.incidente.models.TipoOrigem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class IncidenteMapper {
    @Autowired
    private BarragemMapper barragemMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public Incidente convertIncidenteRequestDTOToIncidenteEntity(final IncidenteRequestDTO requestDTO, final Barragem barragem) {
        return Incidente.builder()
                .dataHora(requestDTO.getDataHora())
                .grauRisco(ClassificacaoRisco.valueOf(requestDTO.getGrauRisco()))
                .tipoIncidente(TipoIncidente.valueOf(requestDTO.getAlerta()))
                .origem(TipoOrigem.valueOf(requestDTO.getOrigem()))
                .observacoes(requestDTO.getObservacoes())
                .barragem(barragem)
                .build();
    }

    public IncidenteResponseDTO convertIncidenteEntityToIncidenteResponseDTO(final Incidente incidente) {
        return IncidenteResponseDTO.builder()
                .id(incidente.getId())
                .dataHora(incidente.getDataHora())
                .grauRisco(incidente.getGrauRisco().getRisco())
                .tipo(incidente.getTipoIncidente().getTipo())
                .barragem(barragemMapper.convertBarragemEntityToBarragemResponseDTO(incidente.getBarragem()))
                .build();
    }

    public List<IncidenteResponseDTO> convertListIncidenteEntityToListIncidenteResponseDTO(
            final List<Incidente> incidentes) {
        List<IncidenteResponseDTO> alertasResponseDTO = new ArrayList<>();

        for (Incidente incidente : incidentes) {
            alertasResponseDTO.add(convertIncidenteEntityToIncidenteResponseDTO(incidente));
        }

        return alertasResponseDTO;
    }

    public String convertIncidenteToMensagemIncidente(final Incidente incidente) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(
                MensagemIncidente.builder()
                .dataHora(incidente.getDataHora().toString())
                .grauRisco(incidente.getGrauRisco().getRisco())
                .tipoIncidente(incidente.getTipoIncidente().getTipo())
                .origem(incidente.getOrigem().getTipo())
                .observacoes(incidente.getObservacoes())
                .idBarragem(incidente.getBarragem().getId().toString())
                .nomeBarragem(incidente.getBarragem().getNome())
                .build());
    }

    public IncidenteInfoResponse convertDataToHabitanteInfoResponseDTO(final List<Barragem> barragens) {
        return IncidenteInfoResponse.builder()
                .barragens(barragemMapper.convertListBarragemEntityToListBarragemResponseDTO(barragens))
                .riscos(Arrays.asList(ClassificacaoRisco.values()).stream().map(risco -> {
                    return ClassificacaoRiscoResponseDTO.builder()
                            .valor(risco.name())
                            .risco(risco.getRisco())
                            .build();
                }).toList())
                .incidentes(Arrays.asList(TipoIncidente.values()).stream().map(incidente -> {
                    return TipoIncidenteResponseDTO.builder()
                            .valor(incidente.name())
                            .tipo(incidente.getTipo())
                            .build();
                }).toList())
                .origens(Arrays.asList(TipoOrigem.values()).stream().map(origem -> {
                    return TipoOrigemResponseDTO.builder()
                            .valor(origem.name())
                            .tipo(origem.getTipo())
                            .build();
                }).toList())
                .build();
    }
}
