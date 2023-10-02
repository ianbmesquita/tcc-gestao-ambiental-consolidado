package br.pucmg.sigam.autenticacao.api.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class APIErrorValidation extends APIError {
    private List<FieldError> errors;

    public APIErrorValidation(final int erro,
                              final LocalDateTime timestamp,
                              final String mensagem,
                              final List<FieldError> errors) {
        super(erro, null, timestamp, mensagem);
        this.errors = errors;
    }

    public APIErrorValidation(final List<FieldError> errors) {
        this.errors = errors;
    }
}