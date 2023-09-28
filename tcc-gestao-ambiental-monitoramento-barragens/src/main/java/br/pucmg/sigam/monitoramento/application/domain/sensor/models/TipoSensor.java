package br.pucmg.sigam.monitoramento.application.domain.sensor.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoSensor {
    SENSOR_DE_PRESSAO("Sensor de Pressão"),
    SENSOR_DE_DESLIZAMENTO("Sensor de Deslizamento"),
    SENSOR_DE_NIVEL_DE_AGUA("Sensor de Nível de Água"),
    SENSOR_DE_DEFORMACAO("Sensor de Deformação"),
    SENSOR_DE_TEMPERATURA("Sensor de Temperatura"),
    SENSOR_DE_VIBRACAO("Sensor de Vibração"),
    SENSOR_DE_GEOLOCALIZACAO("Sensor de Geolocalização"),
    SENSOR_DE_GASES("Sensor de Gases"),
    SENSOR_DE_RADAR("Sensor de Radar"),
    OUTRO("Outro");

    private String tipo;
}
