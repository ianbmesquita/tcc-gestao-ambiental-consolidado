package br.pucmg.sigam.monitoramento.application.domain.sensor.models;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.Barragem;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "sensores")
@Entity(name = "sensores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String nome;

    @Nullable
    @Column
    private String fabricante;

    @NotNull
    @Column
    private TipoSensor tipo;

    @ManyToMany
    @JoinTable(
            name = "sensor_barragem",
            joinColumns = @JoinColumn(name = "id_sensor"),
            inverseJoinColumns = @JoinColumn(name = "id_barragem")
    )
    private List<Barragem> barragens;

}
