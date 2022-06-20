package com.salesianos.triana.edu.Tarea.serviceTest.user;

import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.security.UserDetailsServiceImpl;
import com.salesianos.triana.edu.Tarea.services.BaseService;
import com.salesianos.triana.edu.Tarea.user.dto.NewUserRequest;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.model.UserRole;
import com.salesianos.triana.edu.Tarea.user.repo.UserRepository;
import com.salesianos.triana.edu.Tarea.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest extends BaseService<User, Long, UserRepository> {

    @Mock
    private UserRepository userRepository;






    @Mock
    PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return repositorio.findFirstByUsername(username);
    }

    @InjectMocks
    private UserService userService;

    Comentario comentario;
    List<UserRole> userRoles;
    Tarea tarea;
    User user;

    @BeforeEach
    void init(){





        user = new User();
        user.setId(1L);
        user.setUsername("luismi");
        user.setPassword("eyquease");
        user.setRole(UserRole.USER);

        tarea = new Tarea();
        tarea.setId(1L);
        tarea.setTitulo("tareita wena");
        tarea.setFechaInicio(LocalDateTime.of(2022, 9, 10, 1, 1));
        tarea.setFechaFin(LocalDateTime.of(2022, 9, 11, 1, 1));
        tarea.setComentario(new ArrayList<>());



    }

    //Test: Comprobar si añade un usuario de rol User
    //entrada: userService.newUser(userRequest)
    //salida esperada: Se debe devolver el User debido y que este sea de rol User
    @DisplayName("add new User")
    @Test
    void newUser_success(){
        NewUserRequest userRequest= NewUserRequest.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        when(userService.newUser(userRequest)).thenReturn(user);

        assertEquals(user.getRole(), UserRole.USER);
        assertEquals(userService.findByUsername("luismi").get(), user);
    }




}
