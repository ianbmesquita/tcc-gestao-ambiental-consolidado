package br.pucmg.sigam.autenticacao.application.domain.auth.services;

import br.pucmg.sigam.autenticacao.api.dtos.AuthRequestDTO;
import br.pucmg.sigam.autenticacao.api.dtos.AuthResponseDTO;
import br.pucmg.sigam.autenticacao.application.domain.auth.mappers.AuthMapper;
import br.pucmg.sigam.autenticacao.application.domain.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthMapper authMapper;

    public AuthResponseDTO login(final AuthRequestDTO requestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(requestDTO.getLogin(), requestDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return authMapper.convertToAuthResponseDTO(user, token);
    }

}
