package br.pucmg.sigam.monitoramento.application.domain.incidente.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoIncidente {
    INUNDACAO("Inundação"),
    DESLIZAMENTO_TERRA("Deslizamento de Terra"),
    CONTAMINACAO_AGUA("Contaminação da Água"),
    EVACUACAO("Evacuação de Emergência"),
    OUTRO("Outro");

    private String tipo;
}
