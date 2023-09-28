package br.pucmg.sigam.monitoramento.api.dtos;

import br.pucmg.sigam.monitoramento.application.domain.barragem.models.ClassificacaoRisco;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.StatusBarragem;
import br.pucmg.sigam.monitoramento.application.domain.barragem.models.TipoBarragem;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarragemRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String tipo;

    @NotBlank
    private String municipio;

    @NotBlank
    private String estado;

    @NotBlank
    private String risco;

    @NotBlank
    private String status;

    private Long latitude;

    private Long longitude;
}
