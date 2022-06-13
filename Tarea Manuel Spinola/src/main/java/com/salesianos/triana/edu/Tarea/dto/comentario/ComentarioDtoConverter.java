package com.salesianos.triana.edu.Tarea.dto.comentario;

import com.salesianos.triana.edu.Tarea.dto.tarea.GetTareaDto;
import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ComentarioDtoConverter {


    public Comentario createComentarioDtoToComentario(CreateComentarioDto c){

        return Comentario.builder()
                .createdAt(LocalDateTime.now())
                .texto(c.getTexto())
                .createdAt(LocalDateTime.now())
                .build();


    }

   public GetComentarioDto comentarioToGetComentarioDto(Comentario c){

        return GetComentarioDto
                .builder()
                .id(c.getId())
                .nombreTarea(c.getTarea().getTitulo())
                .nombreUser(c.getUser().getUsername())
                .texto(c.getTexto())
                .createdAt(c.getCreatedAt())
                .build();

   }

}

