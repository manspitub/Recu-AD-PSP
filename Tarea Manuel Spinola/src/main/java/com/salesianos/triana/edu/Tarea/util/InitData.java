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
import java.util.Optional;

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


        NewUserRequest usuario1 = NewUserRequest.builder()
                .username("manuelST")
                .password("eyquease")
                .build();
        userService.newUser(usuario1);


        NewUserRequest usuario2 = NewUserRequest.builder()
                .username("andresillo")
                .password("eyquease")
                .build();
        userService.newUser(usuario2);


        NewUserRequest usuario3 = NewUserRequest.builder()
                .username("pepillo")
                .password("eyquease")
                .build();
        userService.newUser(usuario3);

        userService.newUserAdmin(userAdmin);



       Optional<User> andresillo= userService.findByUsername("andresillo");
       Optional<User> pepillo= userService.findByUsername("pepillo");
       Optional<User> manuelST= userService.findByUsername("manuelST");


        Comentario c1 = Comentario.builder()
                .texto("Vaya tela con el ejercicio")
                .user(andresillo.get())
                .createdAt(LocalDateTime.now())
                .build();
        comentarioRepository.save(c1);

        Comentario c2 = Comentario.builder()
                .texto("No entiendo nada profe :(")
                .user(pepillo.get())
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
                .user(manuelST.get())
                .comentario(coments)
                .build();
        tareaRepository.save(t1);









    }

}
