package com.project.Fitness_Tracker_Monolith_Architecture.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    private String jwtSecret = "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private int jwtExpirations = 172800000;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearertoken = request.getHeader("Authorization");
        if(bearertoken!=null && bearertoken.startsWith("Bearer ")){
            return bearertoken.substring(7);
        }
        return null;
    }
    //Token Generation
    public String generateToken(String userID,String role){

        return Jwts.builder()
                .subject(userID)   //Subject -> whom the token refers too?
                .claim("roles", List.of((role)))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime()+jwtExpirations))
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String jwtToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwtToken);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public String getUserIdfromtoken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload().getSubject();

    }

    public Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload();
    }
}
