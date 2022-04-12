package com.gorkemsavran.carrental.authentication.register.service;

import com.gorkemsavran.carrental.authentication.register.controller.request.RegisterFirmRequest;
import com.gorkemsavran.carrental.authentication.register.controller.request.RegisterNormalUserRequest;
import com.gorkemsavran.carrental.common.infra.MessageResponse;
import com.gorkemsavran.carrental.common.infra.MessageStatus;
import com.gorkemsavran.carrental.user.FirmUser;
import com.gorkemsavran.carrental.user.NormalUser;
import com.gorkemsavran.carrental.user.User;
import com.gorkemsavran.carrental.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final UserRepository userRepository;

    @Transactional
    public MessageResponse registerNormalUser(final RegisterNormalUserRequest registerNormalUserRequest) {
        if (isExistsByUsername(registerNormalUserRequest.getUsername()))
            return new MessageResponse("Username is taken!", MessageStatus.ERROR);

        userRepository.save(new NormalUser(registerNormalUserRequest.getUsername(), registerNormalUserRequest.getPassword()));
        return new MessageResponse("You are successfuly registered", MessageStatus.SUCCESS);
    }

    @Transactional
    public MessageResponse registerFirmUser(final RegisterFirmRequest registerFirmRequest) {
        if (isExistsByUsername(registerFirmRequest.getUsername()))
            return new MessageResponse("Username is taken!", MessageStatus.ERROR);

        FirmUser firmUser = new FirmUser(registerFirmRequest.getUsername(), registerFirmRequest.getPassword(), registerFirmRequest.getCity());
        userRepository.save(firmUser);
        return new MessageResponse("You are successfuly registered", MessageStatus.SUCCESS);
    }

    private boolean isExistsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }
}
