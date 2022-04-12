package com.gorkemsavran.carrental.authentication.login.controller;

import com.gorkemsavran.carrental.authentication.login.controller.request.LoginRequest;
import com.gorkemsavran.carrental.authentication.login.controller.response.JwtToken;
import com.gorkemsavran.carrental.authentication.login.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public JwtToken login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

}
