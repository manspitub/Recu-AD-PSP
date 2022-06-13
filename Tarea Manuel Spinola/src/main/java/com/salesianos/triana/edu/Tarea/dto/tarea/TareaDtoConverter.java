package com.salesianos.triana.edu.Tarea.dto.tarea;

import com.salesianos.triana.edu.Tarea.dto.comentario.ComentarioDtoConverter;
import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.repo.ComentarioRepository;
import com.salesianos.triana.edu.Tarea.services.ComentarioService;
import com.salesianos.triana.edu.Tarea.services.TareaService;
import com.salesianos.triana.edu.Tarea.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TareaDtoConverter {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioDtoConverter dtoConverter;
    private final ComentarioService service;

    public Tarea createTareaDtoToTarea(CreateTareaDto c){
    //Deduzco que al crear una tarea no hay que añadirle comentarios en su creación

        return Tarea.builder()
                .titulo(c.getTitulo())
                .fechaInicio(c.getFechaInicio())
                .fechaFin(c.getFechaFin())
                .fechaCreacion(LocalDateTime.now())
                .build();


    }

    public GetTareaDto tareaToGetTareaDto(Tarea t){
        return GetTareaDto
                .builder()
                .id(t.getId())
                .titulo(t.getTitulo())
                .fechaInicio(t.getFechaInicio())
                .fechaFin(t.getFechaFin())
                .fechaCreacion(t.getFechaCreacion())
                .nombreUser(t.getUser().getUsername())
                .comentarios(service.listaTareaToListaGetTareaDto((comentarioRepository.findAllByTarea(t))))
                .build();
    }

}
