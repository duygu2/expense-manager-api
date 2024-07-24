package com.example.expensemanager.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JwtService {

    private static final long EXPIRATION = 600000;
    private static  String SECRET_KEY= "Uid5J4GDJMjDmxJncmkXU/WoIy0YzDl7JQJ3S74AU7epKHq8G6RNA6H+FlF/sJjYItXsfHCVIcw1DEXcUcb4r8jrv4SqNUP+dncOgOZIKGKU3JPKmA/ip6x/n/aRisIN5bOJz5w8uEh3PSxFf58P/Y1qpTZaMITBQ4v4FaHGcisGIfwDQcW40RNVkIDq9sYERDghRUy/+F6SSpCiYnVo3Y4c1wH8/RA2u/wGGRqfc14kxCEn2cqYvHWwf8T+frkw4PTaNDZzbxo4jZb+7jy+kxSsPp2Nc2HPtzQWRALraHqHcWS4lkaRTo+dFgzlwwvNyzidhrve/Anq+GdNhg401LFNCq/IANAW6k2OmmmmtVI=";
    public String generateToken(String username, Map<String, Object> extraClaims) {
        Map<String, Object> claims = new HashMap<>();
        String token = Jwts
                .builder() // token üretmek için
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis())) // token başlangıç süresi
                .expiration(new Date(System.currentTimeMillis()+ EXPIRATION)) // token bitiş süresi
                .claims(extraClaims)
                .signWith(getSigninKey())
                .compact();

        return token;

    }

    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean validateToken(String token) //token geçerli mi diye kontrol ediyor
    {
        return getTokenClaims(token).getExpiration().after(new Date());
    }

    private Claims getTokenClaims(String token){ //tokenı parse ediyoruz burada
        return  Jwts.parser().verifyWith((SecretKey) getSigninKey()).build().parseSignedClaims(token).getPayload();
    }
    public String extractUsername(String token)
    {
        return getTokenClaims(token).getSubject();
    }
    public List<String> extractRoles(String token)
    {
        return getTokenClaims(token).get("roles",List.class);
    }
}