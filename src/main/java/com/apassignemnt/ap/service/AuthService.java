package com.apassignemnt.ap.service;

import com.apassignemnt.ap.Constants;
import com.apassignemnt.ap.entity.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    public String generateToken(AuthRequest authRequest){
        if(Constants.USERNAME.equals(authRequest.getUserName()) && Constants.PASSWORD.equals(authRequest.getPassword())){
            String token = Jwts.builder()
                    .setSubject(authRequest.getUserName())
                    .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
                    .compact();

            return token;
        }else{
            throw new IllegalArgumentException("Invalid Credentials");
        }
    }
}
