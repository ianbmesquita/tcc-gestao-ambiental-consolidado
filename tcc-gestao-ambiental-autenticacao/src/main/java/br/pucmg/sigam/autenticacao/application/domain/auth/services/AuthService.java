package br.pucmg.sigam.autenticacao.application.domain.auth.services;

import br.pucmg.sigam.autenticacao.infra.dataproviders.repositories.UserRepository;
import br.pucmg.sigam.autenticacao.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findByLogin(username);

        if (user == null) {
            throw new BadCredentialsException(Messages.USUARIO_NAO_ENCONTRADO);
        }

        return user;
    }
}
