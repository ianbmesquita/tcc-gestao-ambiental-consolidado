package br.pucmg.sigam.autenticacao.application.domain.user.services;

import br.pucmg.sigam.autenticacao.api.dtos.UserRequestDTO;
import br.pucmg.sigam.autenticacao.api.dtos.UserResponseDTO;
import br.pucmg.sigam.autenticacao.application.domain.user.mappers.UserMapper;
import br.pucmg.sigam.autenticacao.application.domain.user.models.UserRole;
import br.pucmg.sigam.autenticacao.infra.dataproviders.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.pucmg.sigam.autenticacao.utils.Messages.USUARIO_ID_NAO_ENCONTRADO;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserResponseDTO> getAllUsers() {
        return mapper.convertListUserEntityToListUserResponseDTO(repository.findAll());
    }

    public UserResponseDTO saveNewUser(final UserRequestDTO userRequestDTO) {
        var user = repository.save(mapper.convertUserRequestDTOToUserEntity(userRequestDTO));

        return mapper.convertUserEntityToUserResponseDTO(user);
    }

    public UserResponseDTO editUser(final Long id, final UserRequestDTO requestDTO) {
        var userSolicitado = repository.findById(String.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(String.format(USUARIO_ID_NAO_ENCONTRADO, id)));

        userSolicitado.setName(requestDTO.getName());
        userSolicitado.setRole(UserRole.valueOf(requestDTO.getRole()));
        userSolicitado.setLogin(requestDTO.getLogin());
        userSolicitado.setLocked(requestDTO.getLocked());

        var userAtualizado = repository.save(userSolicitado);

        return mapper.convertUserEntityToUserResponseDTO(userAtualizado);
    }

    public void deleteUserById(final Long id) {
        var user = repository.findById(id.toString())
                .orElseThrow(() -> new EntityNotFoundException(String.format(USUARIO_ID_NAO_ENCONTRADO, id)));

        repository.delete(user);
    }
}
