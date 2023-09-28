package br.pucmg.sigam.monitoramento.api.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class APIErrorValidation extends APIError {
    private List<FieldError> errors;

    public APIErrorValidation() {

    }

    public APIErrorValidation(int erro, LocalDateTime timestamp, String mensagem,
                              List<FieldError> errors) {
        super(erro, null, timestamp, mensagem);
        this.errors = errors;
    }

    public APIErrorValidation(List<FieldError> errors) {
        this.errors = errors;
    }
}