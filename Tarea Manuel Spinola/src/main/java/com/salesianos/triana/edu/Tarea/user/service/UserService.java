package com.salesianos.triana.edu.Tarea.user.service;


import com.salesianos.triana.edu.Tarea.services.BaseService;
import com.salesianos.triana.edu.Tarea.user.dto.NewUserRequest;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.model.UserRole;
import com.salesianos.triana.edu.Tarea.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService extends BaseService<User, Long, UserRepository> {


    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return repositorio.findFirstByUsername(username);
    }

    public User newUser(NewUserRequest request){
        return save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.USER)
                .build());
    }

    public User newUserAdmin(NewUserRequest request){
        return save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.ADMIN)
                .build());
    }
}