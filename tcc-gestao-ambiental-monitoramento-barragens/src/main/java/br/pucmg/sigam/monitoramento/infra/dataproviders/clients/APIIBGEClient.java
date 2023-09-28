package br.pucmg.sigam.monitoramento.infra.dataproviders.clients;

import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Estado;
import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Localidade;
import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Municipio;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

import static br.pucmg.sigam.monitoramento.utils.Messages.ERRO_CONSULTA_ESTADOS;
import static br.pucmg.sigam.monitoramento.utils.Messages.ERRO_CONSULTA_MUNICIPIOS;

@Component
@AllArgsConstructor
public class APIIBGEClient {
    private final RestTemplate restTemplate;
    private final String IBGE_ESTADOS_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    private final String IBGE_MUNICIPIOS_URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%s/municipios";

    public List<LinkedHashMap<String, String>> getEstados() {
        var estados = restTemplate.getForObject(IBGE_ESTADOS_URL, List.class);

        if (!estados.isEmpty()) {
            return estados;
        }

        throw new EntityNotFoundException(ERRO_CONSULTA_ESTADOS);
    }

    public List<Municipio> getMunicipiosByEstado(final Long idEstado) {
        var url = String.format(IBGE_MUNICIPIOS_URL, idEstado.toString());
        var municipios = restTemplate.getForObject(url, List.class);

        if (!municipios.isEmpty()) {
            return municipios;
        }

        throw new EntityNotFoundException(ERRO_CONSULTA_MUNICIPIOS);
    }
}
