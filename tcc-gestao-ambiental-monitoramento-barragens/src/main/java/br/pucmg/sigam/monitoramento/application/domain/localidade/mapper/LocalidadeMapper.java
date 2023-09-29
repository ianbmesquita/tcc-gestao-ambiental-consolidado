package br.pucmg.sigam.monitoramento.application.domain.localidade.mapper;

import br.pucmg.sigam.monitoramento.api.dtos.EstadoResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.LocalidadeResponseDTO;
import br.pucmg.sigam.monitoramento.api.dtos.MunicipioResponseDTO;
import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Estado;
import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Localidade;
import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Municipio;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class LocalidadeMapper {
    public LocalidadeResponseDTO convertLocalidadeEntityToLocalidadeResponseDTO(final Localidade localidade) {
        return LocalidadeResponseDTO.builder()
                .cep(localidade.getCep())
                .logradouro(localidade.getLogradouro())
                .complemento(localidade.getComplemento())
                .bairro(localidade.getBairro())
                .cidade(localidade.getLocalidade())
                .uf(localidade.getUf())
                .build();
    }

    public List<EstadoResponseDTO> convertListEstadoEntityToListEstadoResponseDTO(final List<Estado> estados) {
        var estadosDTO = new ArrayList<EstadoResponseDTO>();

        for (Estado estado : estados) {
            estadosDTO.add(EstadoResponseDTO.builder()
                            .id(estado.getId())
                            .sigla(estado.getSigla())
                            .nome(estado.getNome())
                            .build());
        }

        return estadosDTO;
    }

    public List<MunicipioResponseDTO> convertListMunicipioEntityToListMunicipioResponseDTO(final List<Municipio> municipios) {
        var municipiosDTO = new ArrayList<MunicipioResponseDTO>();

        for (Municipio municipio : municipios) {
            municipiosDTO.add(MunicipioResponseDTO.builder()
                    .id(municipio.getId())
                    .nome(municipio.getNome())
                    .build());
        }

        return municipiosDTO;
    }
}
