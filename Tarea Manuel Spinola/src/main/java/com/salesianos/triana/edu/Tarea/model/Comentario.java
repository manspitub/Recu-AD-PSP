package com.salesianos.triana.edu.Tarea.model;

import com.salesianos.triana.edu.Tarea.user.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comentario {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private String texto;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    private Tarea tarea;

    @ManyToOne
    private User user;

    public void addTarea(Tarea t){
        this.tarea = t;
        t.getComentario().add(this);
    }

    public void removeTarea(Tarea t){
        t.getComentario().remove(this);
        this.tarea = null;
    }


}
