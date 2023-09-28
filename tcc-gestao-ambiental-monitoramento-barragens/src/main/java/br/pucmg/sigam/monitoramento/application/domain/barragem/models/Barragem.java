package br.pucmg.sigam.monitoramento.application.domain.barragem.models;

import br.pucmg.sigam.monitoramento.application.domain.incidente.models.Incidente;
import br.pucmg.sigam.monitoramento.application.domain.sensor.models.Sensor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "barragens")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Barragem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private TipoBarragem tipo;

    @Column
    private String municipio;

    @Column
    private String estado;

    @Column
    private ClassificacaoRisco risco;

    @Column
    private StatusBarragem status;

    @Column
    private Long latitude;

    @Column
    private Long longitude;

    @OneToMany(mappedBy = "barragem", cascade = CascadeType.ALL)
    private List<Incidente> incidentes;

    @ManyToMany(mappedBy = "barragens")
    private List<Sensor> sensores;
}
