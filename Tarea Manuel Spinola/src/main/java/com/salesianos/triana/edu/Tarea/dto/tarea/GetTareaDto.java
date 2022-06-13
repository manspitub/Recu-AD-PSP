package com.salesianos.triana.edu.Tarea.dto.tarea;

import com.salesianos.triana.edu.Tarea.dto.comentario.GetComentarioDto;
import com.salesianos.triana.edu.Tarea.model.Comentario;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetTareaDto {

    private Long id;

    private String titulo;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private List<GetComentarioDto> comentarios;

    private String nombreUser;
}
