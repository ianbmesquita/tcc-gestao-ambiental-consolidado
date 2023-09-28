package br.pucmg.sigam.monitoramento.application.domain.localidade.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Estado {
    private Long id;
    private String nome;
    private String sigla;
}
