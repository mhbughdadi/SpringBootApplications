package com.apogee.securitydemo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtils {

    private static final String SECRET_KEY = "secret_key";

    public String generateToken(UserDetails userDetails){
        Map <String ,Object> claims = new HashMap<>();
        return generateToken(claims,userDetails.getUsername());
    }

    private String generateToken(Map<String,Object> claims,String username){

        String token = Jwts.builder().addClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();

        return token ;
    }

    private <R>R extractClaim(String token , Function<Claims ,R> function){

        Claims claims = extractAllClaims(token);
        R  claim = function.apply(claims);
        return claim;
    }

    private Date extractIssuedDate(String token){
        return extractClaim(token,Claims::getIssuedAt);
    }

    public Date extractExpirationDate(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);
    }
    private Claims extractAllClaims(String token){

        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

        return claims;
    }

    public boolean validateToken(String token , UserDetails userDetails){
        String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = extractExpirationDate(token);
        return new Date(System.currentTimeMillis()).after(expirationDate);
    }
}
