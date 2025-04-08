package br.com.dubacchiega.library.config;

import br.com.dubacchiega.library.users.entities.UsersEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenService {

    @Value("${bookstore.security.secret")
    private String secret;

    public String generateToken(UsersEntity user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId().toString())
                .withClaim("name", user.getName())
                .withClaim("username", user.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API Bookstore")
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT verify = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(UUID.fromString(verify.getClaim("userId").asString()))
                    .name(verify.getClaim("name").asString())
                    .username(verify.getClaim("username").asString())
                    .email(verify.getSubject())
                    .build());
        } catch (JWTVerificationException e){
            return Optional.empty();
        }
    }
}
