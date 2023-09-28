package br.pucmg.sigam.autenticacao.application.domain.auth.services;

import br.pucmg.sigam.autenticacao.application.domain.user.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static br.pucmg.sigam.autenticacao.utils.Messages.ERRO_GERAR_TOKEN;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String apiSecret;

    public String generateToken(final User user) {
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.create()
                    .withIssuer("sigam-autenticacao")
                    .withSubject(user.getLogin())
                    .withExpiresAt(generateExpirationDate())
                    .withClaim("roles", user.getAuthorities().toString())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(ERRO_GERAR_TOKEN, exception);
        }
    }

    public String validateToken(final String token) {
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.require(algorithm)
                    .withIssuer("sigam-autenticacao")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
