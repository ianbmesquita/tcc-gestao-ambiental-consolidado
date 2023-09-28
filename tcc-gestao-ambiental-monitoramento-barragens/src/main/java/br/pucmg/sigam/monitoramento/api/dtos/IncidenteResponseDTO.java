package br.pucmg.sigam.monitoramento.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncidenteResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String grauRisco;
    private String tipo;
    private String origem;
    private String observacoes;
    private BarragemResponseDTO barragem;
}
