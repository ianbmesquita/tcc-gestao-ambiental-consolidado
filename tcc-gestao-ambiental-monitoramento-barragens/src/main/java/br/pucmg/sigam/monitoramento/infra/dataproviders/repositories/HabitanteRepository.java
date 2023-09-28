package br.pucmg.sigam.monitoramento.infra.dataproviders.repositories;

import br.pucmg.sigam.monitoramento.application.domain.habitante.models.Habitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitanteRepository extends JpaRepository<Habitante, Long> {
    @Query("SELECT h FROM Habitante h WHERE h.barragem.id = :barragemId")
    List<Habitante> findAllByBarragemId(@Param("barragemId") Long barragemId);
}
