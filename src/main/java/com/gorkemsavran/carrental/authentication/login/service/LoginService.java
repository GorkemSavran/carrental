package com.gorkemsavran.carrental.authentication.login.service;

import com.gorkemsavran.carrental.authentication.login.controller.request.LoginRequest;
import com.gorkemsavran.carrental.authentication.login.controller.response.JwtToken;
import com.gorkemsavran.carrental.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    @Value("${security.jwt.secret-key}")
    String secretKey;

    private final AuthenticationProvider authenticationProvider;


    @Transactional
    public JwtToken login(LoginRequest loginRequest) {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        try {
            Authentication user = authenticationProvider.authenticate(usernamePasswordAuthenticationToken);
            String token = JwtUtil.generateToken(user, secretKey, 15);
            return new JwtToken(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        return null;
    }
}
