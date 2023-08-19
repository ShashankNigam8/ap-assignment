package com.apassignemnt.ap.filter;


import com.apassignemnt.ap.Constants;
import com.apassignemnt.ap.exception.JwtValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(shouldSkipFilter(request)){
            filterChain.doFilter(request, response);
            return;
        }

        String token = extractToken(request);

        if(!StringUtils.hasText(token)){
            throw new JwtValidationException("No Token present.");
        }

        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(Constants.SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            if(!Constants.USERNAME.equals(claims.getSubject())){
                throw new JwtValidationException("You are not authorized.");
            }

            //token is expired or not
            long expirationTime = claims.getExpiration().getTime();
            long currentTime = System.currentTimeMillis();

            if(expirationTime < currentTime){
                throw new JwtValidationException("You are not authorized.");
            }

            filterChain.doFilter(request, response);
        }catch (Exception e){
            log.error("You are not authorized.");
            throw new JwtValidationException("You are not authorized.");
        }
    }

    private boolean shouldSkipFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/swagger-ui") || path.contains("/v3/api-docs");
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer")){
            return header.substring(7);
        }

        return null;
    }
}
