package br.pucmg.sigam.monitoramento.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidenteRequestDTO {
    @NotNull
    @Positive
    private Long idBarragem;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DATE)
    private LocalDate dataHora;

    @NotBlank
    private String grauRisco;

    @NotBlank
    private String alerta;

    @NotBlank
    private String origem;

    private String observacoes;
}
