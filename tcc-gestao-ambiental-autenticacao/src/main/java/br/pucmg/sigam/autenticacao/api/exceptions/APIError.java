package br.pucmg.sigam.autenticacao.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIError {
    private int error;
    private String status;
    private LocalDateTime timestamp;
    private String message;
}