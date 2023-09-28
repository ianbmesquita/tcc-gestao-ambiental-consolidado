package br.pucmg.sigam.monitoramento.infra.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    Logger log = LogManager.getLogger(JwtProvider.class);

    @Value("${api.security.token.secret}")
    private String jwtSecret;

    public DecodedJWT validateTokenJWT(final String token) {
        try {
            var algorithm = Algorithm.HMAC256(jwtSecret);

            return JWT.require(algorithm)
                    .withIssuer("sigam-autenticacao")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Ocorreu erro na validação do token");
        }
    }
}
