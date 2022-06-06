package com.salesianos.triana.edu.Tarea.user.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JwtUserResponse {

    private Long id;
    private String username;
    private String token;

}