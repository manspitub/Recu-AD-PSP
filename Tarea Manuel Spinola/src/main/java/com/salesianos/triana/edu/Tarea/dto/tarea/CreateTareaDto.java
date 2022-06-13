package com.salesianos.triana.edu.Tarea.dto.tarea;

import com.salesianos.triana.edu.Tarea.model.Comentario;
import com.salesianos.triana.edu.Tarea.user.model.User;
import com.salesianos.triana.edu.Tarea.validadores.anotaciones.FechaAsignacionValueMatch;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FechaAsignacionValueMatch(
        fechaInicioField = "fechaInicio",
        fechaFinField = "fechaFin",
        message = "Las fechas no pueden ser pasadas"
)
public class CreateTareaDto {

    @NotEmpty(message = "No puede estar vacío")
    private String titulo;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

}
