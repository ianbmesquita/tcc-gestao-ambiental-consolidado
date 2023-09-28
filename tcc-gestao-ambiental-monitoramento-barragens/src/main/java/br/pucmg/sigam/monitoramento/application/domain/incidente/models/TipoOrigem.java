package br.pucmg.sigam.monitoramento.application.domain.incidente.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoOrigem {
    OPERADOR("Operador"),
    SENSOR("Sensor");

    private String tipo;
}
