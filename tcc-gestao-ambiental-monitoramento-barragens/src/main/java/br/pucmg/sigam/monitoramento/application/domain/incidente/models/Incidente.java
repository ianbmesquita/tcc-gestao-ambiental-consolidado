package br.pucmg.sigam.monitoramento.application.domain.incidente.models;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "incidentes")
@Entity(name = "incidentes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private LocalDate dataHora;

    @NotNull
    @Column
    private ClassificacaoRisco grauRisco;

    @NotNull
    @Column
    private TipoIncidente tipoIncidente;

    @NotNull
    @Column
    private TipoOrigem origem;

    @Nullable
    @Column
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_barragem")
    private Barragem barragem;
}
