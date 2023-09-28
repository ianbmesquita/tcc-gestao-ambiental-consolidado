package br.pucmg.sigam.monitoramento.application.domain.localidade.service;

import br.pucmg.sigam.monitoramento.api.dtos.EstadoResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.LocalidadeResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.MunicipioResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.localidade.mapper.LocalidadeMapper;
import br.pucmg.sigam.monitoramento.infra.dataproviders.clients.APIIBGEClient;
import br.pucmg.sigam.monitoramento.infra.dataproviders.clients.APIViaCEPClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {
    @Autowired
    private LocalidadeMapper mapper;

    @Autowired
    private APIViaCEPClient cepAPIClient;

    @Autowired
    private APIIBGEClient ibgeAPIClient;

    public LocalidadeResponseDTO consultarLocalidadeViaAPI(final String cep) {
        return mapper.convertLocalidadeEntityToLocalidadeResponseDTO(cepAPIClient.getLocalidadeByCEP(cep));
    }

    public List<EstadoResponseDTO> consultarEstadosBrasileiros() {
        return mapper.convertListEstadoEntityToListEstadoResponseDTO(ibgeAPIClient.getEstados());
    }

    public List<MunicipioResponseDTO> consultarMunicipiosPorIdEstado(final Long idEstado) {
        return mapper.convertListMunicipioEntityToListMunicipioResponseDTO(ibgeAPIClient.getMunicipiosByEstado(idEstado));
    }
}
