package br.pucmg.sigam.monitoramento.infra.dataproviders.repositories;

import br.pucmg.sigam.monitoramento.application.domain.sensor.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
