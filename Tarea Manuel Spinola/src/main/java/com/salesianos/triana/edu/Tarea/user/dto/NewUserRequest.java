package com.salesianos.triana.edu.Tarea.user.dto;

import com.salesianos.triana.edu.Tarea.validadores.anotaciones.FieldsValueMatch;
import com.salesianos.triana.edu.Tarea.validadores.anotaciones.PasswordMatch;
import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Component;

@Value
@Builder
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "verifyPassword",
                message = "Las contraseñas no coinciden"
        )
                })
@PasswordMatch(
        message = "La contraseña no cumple con las características mínimas",
        passwordField = "password"
)
public class NewUserRequest {

    private String username;
    private String password;
    private String verifyPassword;
}