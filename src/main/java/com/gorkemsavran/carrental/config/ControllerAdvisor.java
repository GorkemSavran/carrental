package com.gorkemsavran.carrental.config;

import com.gorkemsavran.carrental.common.infra.MessageResponse;
import com.gorkemsavran.carrental.common.infra.MessageStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(RuntimeException.class)
    public MessageResponse handleException(RuntimeException ex) {

        return new MessageResponse(ex.getMessage(), MessageStatus.ERROR);
    }

    @ExceptionHandler(ClassCastException.class)
    public MessageResponse handleClassCastException(ClassCastException ex) {

        return new MessageResponse("Please login with suitable user", MessageStatus.ERROR);
    }
}
