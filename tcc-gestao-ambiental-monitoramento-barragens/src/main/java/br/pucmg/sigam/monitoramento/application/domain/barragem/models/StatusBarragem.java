package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusBarragem {
    NORMAL("Normal"),
    ATENCAO("Atenção"),
    CRITICO("Critico");

    private String status;
}
