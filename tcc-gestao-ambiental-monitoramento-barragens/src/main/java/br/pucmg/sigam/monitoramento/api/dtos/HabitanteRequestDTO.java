package br.pucmg.sigam.monitoramento.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabitanteRequestDTO {
    @NotBlank
    private String nome;

    @NotNull
    @PastOrPresent
    @DateTimeFormat(iso = DATE)
    private LocalDate nascimento;

    @Pattern(
            regexp = "^\\(\\d{2}\\)\\d{9}$|^\\(\\d{2}\\)\\d{8}$",
            message = "Número de telefone com formato inválido. \nUtilize os formatos: (99)999999999 ou (99)99999999."
    )
    private String telefone;

    @Email
    private String email;

    @NotNull
    private Long idBarragem;

    @NotNull
    @Valid
    private EnderecoRequestDTO endereco;
}
