package com.mauarcanjo.todo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jet-expiration-milliseconds}")
    private long jwtExpirationDate;

    //GEnerate JWT Token
    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();
    }

    private SecretKey key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // Get username from JWT Token
    public String getUsername(String token){

        return Jwts.parser()                // cria o parser
                .verifyWith(key())          // usa a chave para validar a assinatura
                .build()                    // constrói o parser configurado
                .parseSignedClaims(token)   // faz o parse do token (decodifica e valida)
                .getPayload()               // obtém as "claims" do token
                .getSubject();              // retorna o campo "sub" = username
    }

    //Validate JWT Token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token);

        return true;
    }

}
