package com.noticias.notice.jwt;

import com.noticias.notice.dto.jwt.JwtDto;
import com.noticias.notice.entity.UserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        logger.info("PRUEBA MILI");
        logger.info(new Date(new Date().getTime()).toString());
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(new Date().getTime() + expiration))
                .claim("roles",getRoles(userPrincipal))
                .signWith(getKey(secret))
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().verifyWith((SecretKey) getKey(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith((SecretKey) getKey(secret)).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("token expired");
        }
        catch (SignatureException e) {
            logger.error("token error signature");
        }
        catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (MalformedJwtException e) {
            logger.error("token malformated");
        } catch (SecurityException e) {
            logger.error("token bad signature");
        } catch (IllegalArgumentException e) {
            logger.error("token argument ilegal");
        }
        return false;
    }

    private List<String> getRoles(UserPrincipal userDetails) {
        return userDetails.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
    public Key getKey(String secret) {
        byte [] secretsBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretsBytes);
    }

}