package com.salesianos.triana.edu.Tarea.serviceTest.tarea;

import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.repo.ComentarioRepository;
import com.salesianos.triana.edu.Tarea.repo.TareaRepository;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.user.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private ComentarioRepository comentarioRepository;



    Comentario comentario;

    List<UserRole> userRoles;
    Tarea tarea;
    User user;
    List<Comentario> comentarios;
    List<Tarea> tareas;


    @BeforeEach
    void init(){



        user = new User();
        user.setId(1L);
        user.setUsername("luismi");
        user.setPassword("eyquease");
        user.setRole(UserRole.USER);


        comentario = new Comentario();
        comentario.setUser(user);
        comentario.setTexto("esto que eeees");
        comentario.setId(1L);

        comentarios = new ArrayList<>();

        comentarios.add(comentario);

        tarea = new Tarea();
        tarea.setId(1L);
        tarea.setTitulo("tareita wena");
        tarea.setFechaInicio(LocalDateTime.of(2022, 9, 10, 1, 1));
        tarea.setFechaFin(LocalDateTime.of(2022, 9, 11, 1, 1));
        tarea.setComentario(comentarios);

        tareas = new ArrayList<>();
        tareas.add(tarea);

    }

    @Test
    void checkIfTareaHasComments_success(){

        when(tareaRepository.findByUser(user)).thenReturn(tareas);

        assertNotEquals(tarea.getComentario(), null);
    }

    @Test
    void addComment_success(){
        when(tarea.getComentario()).thenReturn(comentarios);

        assertAll(
                ()-> assertEquals(comentario.getTexto(), tarea.getComentario().get(0).getTexto()),
                ()-> assertEquals(comentario.getId(), 1L)
        );
    }



}
