package br.pucmg.sigam.autenticacao.application.domain.user.mappers;

import br.pucmg.sigam.autenticacao.api.dtos.UserRequestDTO;
import br.pucmg.sigam.autenticacao.api.dtos.UserResponseDTO;
import br.pucmg.sigam.autenticacao.application.domain.user.models.User;
import br.pucmg.sigam.autenticacao.application.domain.user.models.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public User convertUserRequestDTOToUserEntity(final UserRequestDTO requestDTO) {
        return User.builder()
                .name(requestDTO.getName())
                .login(requestDTO.getLogin())
                .password(new BCryptPasswordEncoder().encode(requestDTO.getPassword()))
                .role(UserRole.valueOf(requestDTO.getRole()))
                .locked(Boolean.FALSE)
                .build();
    }

    public UserResponseDTO convertUserEntityToUserResponseDTO(final User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .role(user.getRole().toString())
                .locked(user.getLocked())
                .build();
    }

    public List<UserResponseDTO> convertListUserEntityToListUserResponseDTO(final List<User> users) {
        ArrayList usersResponseDTO = new ArrayList();

        for (User user: users) {
            usersResponseDTO.add(convertUserEntityToUserResponseDTO(user));
        }

        return usersResponseDTO;
    }
}
