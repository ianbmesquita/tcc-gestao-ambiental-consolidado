package br.pucmg.sigam.monitoramento.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitanteResponseDTO {
    private Long id;
    private String nome;
    private LocalDate nascimento;
    private String telefone;
    private String email;
    private String barragem_proxima;
    private EnderecoResponseDTO endereco;
}
