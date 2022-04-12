package com.gorkemsavran.carrental.authentication.register.controller;

import com.gorkemsavran.carrental.authentication.register.controller.request.RegisterFirmRequest;
import com.gorkemsavran.carrental.authentication.register.controller.request.RegisterNormalUserRequest;
import com.gorkemsavran.carrental.authentication.register.service.RegisterService;
import com.gorkemsavran.carrental.common.infra.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register/normaluser")
    public MessageResponse registerNormalUser(@Valid @RequestBody RegisterNormalUserRequest registerNormalUserRequest) {
        return registerService.registerNormalUser(registerNormalUserRequest);
    }

    @PostMapping("/register/firmuser")
    public MessageResponse registerFirmUser(@Valid @RequestBody RegisterFirmRequest registerFirmRequest) {
        return registerService.registerFirmUser(registerFirmRequest);
    }
}
