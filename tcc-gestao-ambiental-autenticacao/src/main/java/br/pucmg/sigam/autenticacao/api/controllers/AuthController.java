package br.pucmg.sigam.autenticacao.api.controllers;

import br.pucmg.sigam.autenticacao.api.dtos.AuthRequestDTO;
import br.pucmg.sigam.autenticacao.application.domain.auth.services.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthRequestDTO requestDTO) throws Exception {
        return ResponseEntity.ok().body(loginService.login(requestDTO));
    }
}
