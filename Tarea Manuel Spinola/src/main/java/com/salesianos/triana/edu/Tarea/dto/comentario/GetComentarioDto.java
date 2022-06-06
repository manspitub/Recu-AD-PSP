package com.salesianos.triana.edu.Tarea.dto.comentario;

import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetComentarioDto {

    private Long id;

    private String texto;

    private LocalDateTime createdAt;

    private Tarea tarea;

    private User user;
}
