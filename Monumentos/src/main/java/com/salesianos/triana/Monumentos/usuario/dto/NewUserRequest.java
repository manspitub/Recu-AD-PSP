package com.salesianos.triana.Monumentos.usuario.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@Builder
public class NewUserRequest {

    private String username;
    private String password;
    private String verifyPassword;
}
