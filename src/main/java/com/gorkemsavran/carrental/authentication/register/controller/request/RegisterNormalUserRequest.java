package com.gorkemsavran.carrental.authentication.register.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterNormalUserRequest {

    @NotNull
    @Size(min = 6, max = 40)
    private String username;

    @NotNull
    @Size(min = 6, max = 40)
    private String password;

}
