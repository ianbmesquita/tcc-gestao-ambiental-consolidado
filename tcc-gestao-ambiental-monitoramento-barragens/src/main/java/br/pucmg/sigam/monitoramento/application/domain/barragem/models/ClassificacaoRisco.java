package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClassificacaoRisco {
    BAIXO("Baixo"),
    MEDIO("Médio"),
    ALTO("Alto");

    private String risco;
}
