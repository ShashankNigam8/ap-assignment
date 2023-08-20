package com.apassignemnt.ap.service;

import com.apassignemnt.ap.Constants;
import com.apassignemnt.ap.entity.AuthRequest;
import com.apassignemnt.ap.exception.NoAuthException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AuthService {

    public String generateToken(AuthRequest authRequest){
        if(Constants.USERNAME.equals(authRequest.getUserName()) && Constants.PASSWORD.equals(authRequest.getPassword())){
            String token = Jwts.builder()
                    .setSubject(authRequest.getUserName())
                    .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                    .compact();

            log.info("Token generated Successfully!!!");
            return token;
        }else{
            log.error("Invalid credentials, please contact admin.");
            throw new NoAuthException("Invalid Credentials");
        }
    }
}
