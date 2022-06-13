package com.salesianos.triana.edu.Tarea.dto.comentario;

import com.salesianos.triana.edu.Tarea.model.Tarea;
import com.salesianos.triana.edu.Tarea.user.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreateComentarioDto {

    @NotEmpty(message = "No puede estar vacío")
    private String texto;

    private LocalDateTime createdAt;

    private Long tareaId;

}
