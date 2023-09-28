package br.pucmg.sigam.monitoramento.infra.dataproviders.repositories;

import br.pucmg.sigam.monitoramento.application.domain.incidente.models.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
}
