package br.pucmg.sigam.autenticacao.application.domain.auth.mappers;

import br.pucmg.sigam.autenticacao.api.dtos.AuthResponseDTO;
import br.pucmg.sigam.autenticacao.application.domain.user.models.User;
import br.pucmg.sigam.autenticacao.infra.dataproviders.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    private final UserRepository userRepository;

    public AuthMapper(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponseDTO convertToAuthResponseDTO(final User user, final String token) {
        return AuthResponseDTO.builder()
                .name(user.getName())
                .role(user.getRole().toString())
                .locked(user.getLocked())
                .token(token)
                .build();
    }
}
