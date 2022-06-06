package com.salesianos.triana.edu.Tarea.model;


import com.salesianos.triana.edu.Tarea.user.model.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Tarea {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private String titulo;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    @OneToMany(mappedBy = "tarea")
    private List<Comentario> comentario;

    @ManyToOne()
    private User user;

    public List<String> getComentariosTarea(){
        List<String> comentsTarea = new ArrayList<>();

        for (Comentario comentarios: comentario){
            comentsTarea.add("Texto: "+comentarios.getTexto());
            comentsTarea.add("Created at: "+comentarios.getCreatedAt().toString());
        }
        return comentsTarea;
    }

    public Tarea(String titulo, LocalDateTime fechaCreacion, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
