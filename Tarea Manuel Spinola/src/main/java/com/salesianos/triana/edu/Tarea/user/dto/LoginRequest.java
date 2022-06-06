package com.salesianos.triana.edu.Tarea.user.dto;

import lombok.Value;


@Value
public class LoginRequest {

    private Long id;
    private String username, password;

}