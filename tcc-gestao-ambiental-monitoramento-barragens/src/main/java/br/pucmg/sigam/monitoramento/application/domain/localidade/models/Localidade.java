package br.pucmg.sigam.monitoramento.application.domain.localidade.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Localidade {
    private String cep;
    private String logradouro;
    private String complemento;
    private String localidade;
    private String bairro;
    private String cidade;
    private String uf;
}
