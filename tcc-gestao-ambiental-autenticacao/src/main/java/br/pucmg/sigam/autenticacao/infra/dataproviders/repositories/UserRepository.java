package br.pucmg.sigam.autenticacao.infra.dataproviders.repositories;

import br.pucmg.sigam.autenticacao.application.domain.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByLogin(final String login);
}
