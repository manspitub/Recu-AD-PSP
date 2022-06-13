package com.salesianos.triana.edu.Tarea.user.controller;


import com.salesianos.triana.edu.Tarea.security.jwt.JwtProvider;
import com.salesianos.triana.edu.Tarea.user.dto.JwtUserResponse;
import com.salesianos.triana.edu.Tarea.user.dto.LoginRequest;
import com.salesianos.triana.edu.Tarea.user.dto.NewUserRequest;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService usuarioServicio;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        User nuevo = usuarioServicio.newUser(newUserRequest);

        String jwt = doLogin(newUserRequest.getUsername(),
                newUserRequest.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.builder()
                        .id(nuevo.getId())
                        .username(nuevo.getUsername())
                        .token(jwt)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        String jwt = doLogin(loginRequest.getUsername(), loginRequest.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.builder()
                        .id(loginRequest.getId())
                        .username(loginRequest.getUsername())
                        .token(jwt)
                        .build());

    }


    private String doLogin(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Devolver una respuesta adecuada
        // que incluya el token del usuario.
        return jwtProvider.generateToken(authentication);
    }

}