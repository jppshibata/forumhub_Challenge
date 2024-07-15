package com.forumhub_challenge.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forumhub_challenge.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "API Forum_Hub";

    public String tokenGenerate(User user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
          return JWT.create()
                    .withIssuer(ISSUER)
                  .withSubject(user.getLogin())
                  .withClaim("id", user.getId())
                  .withExpiresAt(ExpireDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar Token jwt", exception);
        }
    }

    public String getSubject (String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT Inválido ou Expirado!" +tokenJWT);

        }
    }

    private Instant ExpireDate() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
