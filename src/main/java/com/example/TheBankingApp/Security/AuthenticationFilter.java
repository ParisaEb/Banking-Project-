package com.example.TheBankingApp.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.TheBankingApp.Dtos.TokenDto;
import com.example.TheBankingApp.Dtos.UserDetailDto;
import com.example.TheBankingApp.Utils.JWTUtil;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    public static final String USER_LOGIN_PATH = "/parisa";
    private final JWTUtil jwtUtil;

    
    public AuthenticationFilter(JWTUtil jwtUtil ) {
    	this.jwtUtil=jwtUtil;
    	
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equals(USER_LOGIN_PATH)) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String accessToken = authorizationHeader.substring("Bearer ".length());
                TokenDto decodedJWT = jwtUtil.getDecodedToken(accessToken);
                UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(decodedJWT);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(TokenDto decodedJWT) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(decodedJWT.getUsername(), null, decodedJWT.getAuthorities());
        UserDetailDto userDetailsDto = new UserDetailDto();
        userDetailsDto.setName(decodedJWT.getUsername());
        userDetailsDto.setUserId(decodedJWT.getUserId());
        userDetailsDto.setName(decodedJWT.getUserFullName());
        authenticationToken.setDetails(userDetailsDto);
        return authenticationToken;
    }


	
		
	}

	
