package com.salesianos.triana.Monumentos.usuario.dto;

import lombok.Value;


@Value
public class LoginRequest {

    private Long id;
    private String username, password;

}