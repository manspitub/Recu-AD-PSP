package com.salesianos.triana.edu.Tarea.dto.tarea;

import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.services.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TareaDtoConverter {

    private final TareaService service;

    public Tarea createTareaDtoToTarea(CreateTareaDto c){
    //Deduzco que al crear una tarea no hay que añadirle comentarios en su creación

        return Tarea.builder()
                .titulo(c.getTitulo())
                .fechaInicio(c.getFechaInicio())
                .fechaFin(c.getFechaFin())
                .user(service.addUser(c.getUserId()))
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
                .comentarios(t.getComentariosTarea())
                .build();
    }

}
