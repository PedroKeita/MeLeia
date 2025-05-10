package com.meleia.meleia.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.meleia.meleia.model.user.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario){
        try{
            //HM
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("api-authorization")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(generationExpirationDate())
                    .sign(algorithm);
            return token;

        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao GERAR JWT: ", exception);

        }
    }


    public String validationToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-authorization")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception) {
            return "";

        }
    }

    private Instant generationExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
