package com.salesianos.triana.edu.Tarea.util;

import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.repo.ComentarioRepository;
import com.salesianos.triana.edu.Tarea.repo.TareaRepository;
import com.salesianos.triana.edu.Tarea.user.dto.NewUserRequest;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.model.UserRole;
import com.salesianos.triana.edu.Tarea.user.repo.UserRepository;
import com.salesianos.triana.edu.Tarea.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {


    private final TareaRepository tareaRepository;
    private final ComentarioRepository comentarioRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @PostConstruct
    public void init() {

        NewUserRequest userAdmin = NewUserRequest.builder()
                .username("manuelAdmin")
                .password("manolito")
                .build();

        User usuario1 = User.builder()
                .username("manuelST")
                .password("eyquease")
                .role(UserRole.USER)
                .build();
        userRepository.save(usuario1);

        User usuario2 = User.builder()
                .username("andresillo")
                .password("eyquease")
                .role(UserRole.USER)
                .build();
        userRepository.save(usuario2);

        User usuario3 = User.builder()
                .username("pepillo")
                .password("eyquease")
                .role(UserRole.USER)
                .build();
        userRepository.save(usuario3);

        userService.newUserAdmin(userAdmin);





        Comentario c1 = Comentario.builder()
                .texto("Vaya tela con el ejercicio")
                .user(usuario2)
                .createdAt(LocalDateTime.now())
                .build();
        comentarioRepository.save(c1);

        Comentario c2 = Comentario.builder()
                .texto("No entiendo nada profe :(")
                .user(usuario3)
                .createdAt(LocalDateTime.now())
                .build();
        comentarioRepository.save(c2);

        List<Comentario> coments = new ArrayList<>();



        coments.add(c1);
        coments.add(c2);

        Tarea t1 = Tarea.builder()
                .titulo("Ejercicio 5 AD")
                .fechaInicio(LocalDateTime.of(2022, 3, 10, 1, 1, 1))
                .fechaFin(LocalDateTime.of(2022, 10, 1, 1, 1))
                .fechaCreacion(LocalDateTime.now())
                .user(usuario1)
                .comentario(coments)
                .build();
        tareaRepository.save(t1);









    }

}
