package br.pucmg.sigam.monitoramento.application.domain.habitante.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "enderecos")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String cep;

    @NotNull
    @Column
    private String logradouro;

    @NotNull
    @Column
    private Integer numero;

    @Nullable
    @Column
    private String complemento;

    @NotNull
    @Column
    private String municipio;

    @NotNull
    @Column
    private String estado;
}
