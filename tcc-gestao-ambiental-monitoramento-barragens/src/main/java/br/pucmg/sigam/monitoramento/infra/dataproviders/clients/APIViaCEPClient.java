package br.pucmg.sigam.monitoramento.infra.dataproviders.clients;

import br.pucmg.sigam.monitoramento.application.domain.localidade.models.Localidade;
import br.pucmg.sigam.monitoramento.utils.Messages;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static br.pucmg.sigam.monitoramento.utils.Messages.CEP_INFORMADO_INVALIDO;

@Component
@AllArgsConstructor
public class APIViaCEPClient {

    private final RestTemplate restTemplate;
    private final String VIACEP_URL = "https://viacep.com.br/ws/";

    public Localidade getLocalidadeByCEP(final String cep) {
        var url = VIACEP_URL + cep + "/json";

        var localidade = restTemplate.getForObject(url, Localidade.class);

        if (localidade.getCep() != null) {
            return localidade;
        }

        throw new EntityNotFoundException(CEP_INFORMADO_INVALIDO);
    }
}
