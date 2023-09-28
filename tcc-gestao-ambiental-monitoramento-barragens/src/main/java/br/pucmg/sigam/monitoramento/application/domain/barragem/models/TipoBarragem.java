package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoBarragem {
    CONCRETO("Concreto"),
    ENROCAMENTO("Enrocamento"),
    ATERRO("Aterro");

    private String tipo;
}
