package br.pucmg.sigam.monitoramento.application.domain.habitante.models;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Table(name = "habitantes")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Habitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String nome;

    @Nullable
    @Column
    private LocalDate nascimento;

    @NotNull
    @Column
    private String telefone;

    @NotNull
    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_endereco", referencedColumnName = "id", nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_barragem", referencedColumnName = "id")
    private Barragem barragem;

}