package com.example.TheBankingApp.Utils;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.TheBankingApp.Dtos.TokenDto;
import com.example.TheBankingApp.Dtos.UserDetailDto;



@Component
public class JWTUtil {
    public static final String USER_ID = "userId";
    public static final String NAME = "userFullName";  
    public static final String SALT = "salt";
    public static final String ROLES = "roles";
    
   
    private Integer jtwTtl=10;
 
    private String jwtSecret="Secret";
   
    
    public String getJWTToken(UserDetailDto user) {
        //todo: ECDSA512
        Algorithm algorithm = getAlgorithm();
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim(USER_ID, user.getUserId())
                .withClaim(NAME, user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000L * 60 * jtwTtl)))
                .withClaim(SALT, UUID.randomUUID().toString())
                .withClaim(ROLES, user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).toList())
                .sign(algorithm);
    }
    
    public TokenDto getDecodedToken(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        TokenDto tokenDto = new TokenDto();
        tokenDto.setUsername(decodedJWT.getSubject());
        tokenDto.setUserId(decodedJWT.getClaim(USER_ID).asLong());
        tokenDto.setUserFullName(decodedJWT.getClaim(NAME).asString());
       // tokenDto.setAuthorities(getGrantedAuthorities(decodedJWT));
        return tokenDto;
    }

    public DecodedJWT getDecodedJWT(String accessToken) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(accessToken);
    }

    private Set<GrantedAuthority> getGrantedAuthorities(DecodedJWT decodedJWT) {
        Claim claim = decodedJWT.getClaim(ROLES);
        List<String> roles = claim.asList(String.class);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        for (String role : roles) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(role));
        }
        return grantedAuthoritySet;
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtSecret);
    }
}


