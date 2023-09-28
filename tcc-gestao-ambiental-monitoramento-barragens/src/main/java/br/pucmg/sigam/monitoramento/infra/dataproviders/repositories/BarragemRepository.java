package br.pucmg.sigam.monitoramento.infra.dataproviders.repositories;

import br.pucmg.sigam.monitoramento.api.dtos.BarragemRequestDTO;
import br.pucmg.sigam.monitoramento.application.domain.barragem.filters.BarragemFilter;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarragemRepository extends JpaRepository<Barragem, Long> {
    @Query("SELECT b FROM Barragem b " +
           "WHERE (:#{#filtro.nome} IS NULL OR b.nome LIKE %:#{#filtro.nome}%) " +
           "AND (:#{#filtro.estado} IS NULL OR b.estado LIKE %:#{#filtro.estado}%) " +
           "AND (:#{#filtro.municipio} IS NULL OR b.municipio LIKE %:#{#filtro.municipio}%) " +
           "AND (:#{#filtro.tipo} IS NULL OR b.tipo = :#{#filtro.tipo}) " +
           "AND (:#{#filtro.risco} IS NULL OR b.risco = :#{#filtro.risco}) " +
           "AND (:#{#filtro.status} IS NULL OR b.status = :#{#filtro.status}) "
    )
    List<Barragem> findBarragensByParams(@Param("filtro") BarragemFilter filtro);
}
