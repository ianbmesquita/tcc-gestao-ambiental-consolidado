package br.pucmg.sigam.autenticacao.application.domain.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    OUTSOURCED("outsourced");

    private String role;
}
